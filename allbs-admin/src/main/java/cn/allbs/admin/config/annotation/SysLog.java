package cn.allbs.admin.config.annotation;

import java.lang.annotation.*;

/**
 * 采集日志得注解
 *
 * @author ChenQi
 * @date 2024/3/8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    /**
     * 操作内容
     */
    String value() default "";
}
