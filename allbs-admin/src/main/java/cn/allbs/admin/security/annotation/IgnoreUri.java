package cn.allbs.admin.security.annotation;

import java.lang.annotation.*;

/**
 * 接口 IgnoreUri 添加注解用于放行接口
 *
 * @author ChenQi
 * @date 2024/3/8
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreUri {

    boolean value() default true;
}
