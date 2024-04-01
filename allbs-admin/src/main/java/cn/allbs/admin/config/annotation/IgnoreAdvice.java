package cn.allbs.admin.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解 IgnoreAdvice
 *
 * @author ChenQi
 * @date 2024/4/1
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreAdvice {
}
