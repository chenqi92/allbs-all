package cn.allbs.admin.config.annotation;

import java.lang.annotation.*;

/**
 * 注解 CopyProperty
 *
 * @author ChenQi
 * @date 2024/4/1
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CopyProperty {

    /**
     * 属性名，用于指定别名，默认使用：field name
     *
     * @return 属性名
     */
    String value() default "";

    /**
     * 忽略：默认为 false
     *
     * @return 是否忽略
     */
    boolean ignore() default false;
}
