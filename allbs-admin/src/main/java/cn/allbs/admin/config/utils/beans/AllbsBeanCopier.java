package cn.allbs.admin.config.utils.beans;

import cn.allbs.admin.config.annotation.CopyProperty;
import cn.allbs.admin.config.utils.BeanUtil;
import cn.allbs.admin.config.utils.CollectionUtil;
import cn.allbs.admin.config.utils.ReflectUtil;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Label;
import org.springframework.asm.Opcodes;
import org.springframework.asm.Type;
import org.springframework.cglib.core.*;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 类 AllbsBeanCopier
 *
 * @author ChenQi
 * @date 2024/4/1
 */
public abstract class AllbsBeanCopier {

    private static final Type CONVERTER = TypeUtils.parseType(Converter.class.getName());
    private static final Type BEAN_COPIER = TypeUtils.parseType(AllbsBeanCopier.class.getName());
    private static final Type BEAN_MAP = TypeUtils.parseType(Map.class.getName());
    private static final Signature COPY = new Signature("copy", Type.VOID_TYPE, new Type[]{Constants.TYPE_OBJECT, Constants.TYPE_OBJECT, CONVERTER});
    private static final Signature CONVERT = TypeUtils.parseSignature("Object convert(Object, Class, Object)");
    private static final Signature BEAN_MAP_GET = TypeUtils.parseSignature("Object get(Object)");
    private static final Type CLASS_UTILS = TypeUtils.parseType(org.springframework.util.ClassUtils.class.getName());
    private static final Signature IS_ASSIGNABLE_VALUE = TypeUtils.parseSignature("boolean isAssignableValue(Class, Object)");
    private static final String BEAN_NAME_PREFIX = AllbsBeanCopier.class.getName();
    /**
     * The map to store {@link AllbsBeanCopier} of source type and class type for copy.
     */
    private static final ConcurrentMap<AllbsBeanCopierKey, AllbsBeanCopier> BEAN_COPIER_MAP = new ConcurrentHashMap<>();

    public static AllbsBeanCopier create(Class source, Class target, boolean useConverter) {
        return AllbsBeanCopier.create(source, target, useConverter, false);
    }

    public static AllbsBeanCopier create(Class source, Class target, boolean useConverter, boolean nonNull) {
        AllbsBeanCopierKey copierKey = new AllbsBeanCopierKey(source, target, useConverter, nonNull);
        // 利用 ConcurrentMap 缓存 提高性能，接近 直接 get set
        return CollectionUtil.computeIfAbsent(BEAN_COPIER_MAP, copierKey, key -> {
            Generator gen = new Generator();
            gen.setSource(key.getSource());
            gen.setTarget(key.getTarget());
            gen.setContextClass(AllbsBeanCopier.class);
            gen.setUseConverter(key.isUseConverter());
            gen.setNonNull(key.isNonNull());
            gen.setNamePrefix(BEAN_NAME_PREFIX);
            gen.setUseCache(true);
            return gen.create(key);
        });
    }

    /**
     * Bean copy
     *
     * @param from      from Bean
     * @param to        to Bean
     * @param converter Converter
     */
    public abstract void copy(Object from, Object to, @Nullable Converter converter);

    public static class Generator extends AbstractClassGenerator {
        private static final Source SOURCE = new Source(AllbsBeanCopier.class.getName());
        private Class source;
        private Class target;
        private boolean useConverter;
        private boolean nonNull;

        Generator() {
            super(SOURCE);
        }

        public void setSource(Class source) {
            this.source = source;
        }

        public void setTarget(Class target) {
            this.target = target;
        }

        @Override
        public void setNamePrefix(String namePrefix) {
            super.setNamePrefix(namePrefix);
        }

        public void setUseConverter(boolean useConverter) {
            this.useConverter = useConverter;
        }

        public void setNonNull(boolean nonNull) {
            this.nonNull = nonNull;
        }

        @Override
        protected ClassLoader getDefaultClassLoader() {
            return target.getClassLoader();
        }

        @Override
        protected ProtectionDomain getProtectionDomain() {
            return ReflectUtils.getProtectionDomain(source);
        }

        @Override
        public AllbsBeanCopier create(Object key) {
            return (AllbsBeanCopier) super.create(key);
        }

        @Override
        public void generateClass(ClassVisitor v) {
            Type sourceType = Type.getType(source);
            Type targetType = Type.getType(target);
            ClassEmitter ce = new ClassEmitter(v);
            ce.begin_class(Opcodes.V1_2,
                    Opcodes.ACC_PUBLIC,
                    getClassName(),
                    BEAN_COPIER,
                    null,
                    Constants.SOURCE_FILE);

            EmitUtils.null_constructor(ce);
            CodeEmitter e = ce.begin_method(Opcodes.ACC_PUBLIC, COPY, null);

            // map 单独处理
            if (Map.class.isAssignableFrom(source)) {
                generateClassFormMap(ce, e, sourceType, targetType);
                return;
            }

            // 注意：此处需兼容链式bean 使用了 spring 的方法，比较耗时
            PropertyDescriptor[] getters = ReflectUtil.getBeanGetters(source);
            PropertyDescriptor[] setters = ReflectUtil.getBeanSetters(target);
            Map<String, PropertyDescriptor> names = new HashMap<>(16);
            for (PropertyDescriptor getter : getters) {
                names.put(getter.getName(), getter);
            }

            Local targetLocal = e.make_local();
            Local sourceLocal = e.make_local();
            e.load_arg(1);
            e.checkcast(targetType);
            e.store_local(targetLocal);
            e.load_arg(0);
            e.checkcast(sourceType);
            e.store_local(sourceLocal);

            for (PropertyDescriptor setter : setters) {
                String propName = setter.getName();

                CopyProperty targetIgnoreCopy = ReflectUtil.getAnnotation(target, propName, CopyProperty.class);
                // set 上有忽略的 注解
                if (targetIgnoreCopy != null) {
                    if (targetIgnoreCopy.ignore()) {
                        continue;
                    }
                    // 注解上的别名，如果别名不为空，使用别名
                    String aliasTargetPropName = targetIgnoreCopy.value();
                    if (!StringUtils.hasText(aliasTargetPropName)) {
                        propName = aliasTargetPropName;
                    }
                }
                // 找到对应的 get
                PropertyDescriptor getter = names.get(propName);
                // 没有 get 跳出
                if (getter == null) {
                    continue;
                }

                MethodInfo read = ReflectUtils.getMethodInfo(getter.getReadMethod());
                Method writeMethod = setter.getWriteMethod();
                MethodInfo write = ReflectUtils.getMethodInfo(writeMethod);
                Type returnType = read.getSignature().getReturnType();
                Type setterType = write.getSignature().getArgumentTypes()[0];
                Class<?> getterPropertyType = getter.getPropertyType();
                Class<?> setterPropertyType = setter.getPropertyType();

                // nonNull Label
                Label l0 = e.make_label();
                // 判断类型是否一致，包括 包装类型
                if (org.springframework.util.ClassUtils.isAssignable(setterPropertyType, getterPropertyType)) {
                    e.load_local(targetLocal);
                    e.load_local(sourceLocal);
                    e.invoke(read);
                    boolean getterIsPrimitive = getterPropertyType.isPrimitive();
                    boolean setterIsPrimitive = setterPropertyType.isPrimitive();

                    if (nonNull) {
                        // 需要落栈，强制装箱
                        e.box(returnType);
                        Local var = e.make_local();
                        e.store_local(var);
                        e.load_local(var);
                        // nonNull Label
                        e.ifnull(l0);
                        e.load_local(targetLocal);
                        e.load_local(var);
                        // 需要落栈，强制拆箱
                        e.unbox_or_zero(setterType);
                    } else {
                        // 如果 get 为原始类型，需要装箱
                        if (getterIsPrimitive && !setterIsPrimitive) {
                            e.box(returnType);
                        }
                        // 如果 set 为原始类型，需要拆箱
                        if (!getterIsPrimitive && setterIsPrimitive) {
                            e.unbox_or_zero(setterType);
                        }
                    }
                    // 构造 set 方法
                    invokeWrite(e, write, writeMethod, nonNull, l0);
                } else if (useConverter) {
                    e.load_local(targetLocal);
                    e.load_arg(2);
                    e.load_local(sourceLocal);
                    e.invoke(read);
                    e.box(returnType);

                    if (nonNull) {
                        Local var = e.make_local();
                        e.store_local(var);
                        e.load_local(var);
                        e.ifnull(l0);
                        e.load_local(targetLocal);
                        e.load_arg(2);
                        e.load_local(var);
                    }

                    EmitUtils.load_class(e, setterType);
                    // 更改成了属性名，cglib 默认是 set 方法名
                    e.push(propName);
                    e.invoke_interface(CONVERTER, CONVERT);
                    e.unbox_or_zero(setterType);

                    // 构造 set 方法
                    invokeWrite(e, write, writeMethod, nonNull, l0);
                }
            }
            e.return_value();
            e.end_method();
            ce.end_class();
        }

        private static void invokeWrite(CodeEmitter e, MethodInfo write, Method writeMethod, boolean nonNull, Label l0) {
            // 返回值，判断 链式 bean
            Class<?> returnType = writeMethod.getReturnType();
            e.invoke(write);
            // 链式 bean，有返回值需要 pop
            if (!returnType.equals(Void.TYPE)) {
                e.pop();
            }
            if (nonNull) {
                e.visitLabel(l0);
            }
        }

        @Override
        protected Object firstInstance(Class type) {
            return BeanUtil.newInstance(type);
        }

        @Override
        protected Object nextInstance(Object instance) {
            return instance;
        }

        /**
         * 处理 map 的 copy
         *
         * @param ce         ClassEmitter
         * @param e          CodeEmitter
         * @param sourceType sourceType
         * @param targetType targetType
         */
        private void generateClassFormMap(ClassEmitter ce, CodeEmitter e, Type sourceType, Type targetType) {
            PropertyDescriptor[] setters = ReflectUtil.getBeanSetters(target);

            // 入口变量
            Local targetLocal = e.make_local();
            Local sourceLocal = e.make_local();
            e.load_arg(1);
            e.checkcast(targetType);
            e.store_local(targetLocal);
            e.load_arg(0);
            e.checkcast(sourceType);
            e.store_local(sourceLocal);
            Type mapBox = Type.getType(Object.class);

            for (PropertyDescriptor setter : setters) {
                String propName = setter.getName();

                // set 上有忽略的 注解
                CopyProperty targetIgnoreCopy = ReflectUtil.getAnnotation(target, propName, CopyProperty.class);
                if (targetIgnoreCopy != null) {
                    if (targetIgnoreCopy.ignore()) {
                        continue;
                    }
                    // 注解上的别名
                    String aliasTargetPropName = targetIgnoreCopy.value();
                    if (!StringUtils.hasText(aliasTargetPropName)) {
                        propName = aliasTargetPropName;
                    }
                }

                Method writeMethod = setter.getWriteMethod();
                MethodInfo write = ReflectUtils.getMethodInfo(writeMethod);
                Type setterType = write.getSignature().getArgumentTypes()[0];

                e.load_local(targetLocal);
                e.load_local(sourceLocal);

                e.push(propName);
                // 执行 map get
                e.invoke_interface(BEAN_MAP, BEAN_MAP_GET);
                // box 装箱，避免 array[] 数组问题
                e.box(mapBox);

                // 生成变量
                Local var = e.make_local();
                e.store_local(var);
                e.load_local(var);

                // 先判断 不为null，然后做类型判断
                Label l0 = e.make_label();
                e.ifnull(l0);
                EmitUtils.load_class(e, setterType);
                e.load_local(var);
                // ClassUtils.isAssignableValue(Integer.class, id)
                e.invoke_static(CLASS_UTILS, IS_ASSIGNABLE_VALUE, false);
                Label l1 = new Label();
                // 返回值，判断 链式 bean
                Class<?> returnType = writeMethod.getReturnType();
                if (useConverter) {
                    e.if_jump(Opcodes.IFEQ, l1);
                    e.load_local(targetLocal);
                    e.load_local(var);
                    e.unbox_or_zero(setterType);
                    e.invoke(write);
                    if (!returnType.equals(Void.TYPE)) {
                        e.pop();
                    }
                    e.goTo(l0);
                    e.visitLabel(l1);
                    e.load_local(targetLocal);
                    e.load_arg(2);
                    e.load_local(var);
                    EmitUtils.load_class(e, setterType);
                    e.push(propName);
                    e.invoke_interface(CONVERTER, CONVERT);
                } else {
                    e.if_jump(Opcodes.IFEQ, l0);
                    e.load_local(targetLocal);
                    e.load_local(var);
                }
                e.unbox_or_zero(setterType);
                e.invoke(write);
                // 返回值，判断 链式 bean
                if (!returnType.equals(Void.TYPE)) {
                    e.pop();
                }
                e.visitLabel(l0);
            }
            e.return_value();
            e.end_method();
            ce.end_class();
        }
    }
}
