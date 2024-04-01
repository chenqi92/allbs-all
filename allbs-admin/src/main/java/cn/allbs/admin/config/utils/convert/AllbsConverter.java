package cn.allbs.admin.config.utils.convert;

import cn.allbs.admin.config.function.CheckedFunction;
import cn.allbs.admin.config.utils.CollectionUtil;
import cn.allbs.admin.config.utils.ConvertUtil;
import cn.allbs.admin.config.utils.ReflectUtil;
import cn.allbs.admin.config.utils.Unchecked;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Converter;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 类 AllbsConverter
 *
 * @author ChenQi
 * @date 2024/4/1
 */
@Slf4j
@AllArgsConstructor
public class AllbsConverter implements Converter {
    private static final ConcurrentMap<String, TypeDescriptor> TYPE_CACHE = new ConcurrentHashMap<>();
    private final Class<?> sourceClazz;
    private final Class<?> targetClazz;

    /**
     * cglib convert
     *
     * @param value     源对象属性
     * @param target    目标对象属性类
     * @param fieldName 目标的field名，原为 set 方法名，MicaBeanCopier 里做了更改
     * @return {Object}
     */
    @Override
    @Nullable
    public Object convert(@Nullable Object value, Class target, final Object fieldName) {
        if (value == null) {
            return null;
        }
        // 类型一样，不需要转换
        if (ClassUtils.isAssignableValue(target, value)) {
            return value;
        }
        try {
            TypeDescriptor targetDescriptor = AllbsConverter.getTypeDescriptor(targetClazz, (String) fieldName);
            // 1. 判断 sourceClazz 为 Map
            if (Map.class.isAssignableFrom(sourceClazz)) {
                return ConvertUtil.convert(value, targetDescriptor);
            } else {
                TypeDescriptor sourceDescriptor = AllbsConverter.getTypeDescriptor(sourceClazz, (String) fieldName);
                return ConvertUtil.convert(value, sourceDescriptor, targetDescriptor);
            }
        } catch (Throwable e) {
            log.warn("MicaConverter error", e);
            return null;
        }
    }

    private static TypeDescriptor getTypeDescriptor(final Class<?> clazz, final String fieldName) {
        String srcCacheKey = clazz.getName() + fieldName;
        // 忽略抛出异常的函数，定义完整泛型，避免编译问题
        CheckedFunction<String, TypeDescriptor> uncheckedFunction = (key) -> {
            // 这里 property 理论上不会为 null
            Field field = ReflectUtil.getField(clazz, fieldName);
            if (field == null) {
                throw new NoSuchFieldException(fieldName);
            }
            return new TypeDescriptor(field);
        };
        return CollectionUtil.computeIfAbsent(TYPE_CACHE, srcCacheKey, Unchecked.function(uncheckedFunction));
    }
}
