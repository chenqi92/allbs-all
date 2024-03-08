package cn.allbs.admin.config.annotation;

/**
 * 采集日志得注解
 *
 * @author ChenQi
 * @date 2024/3/8
 */
public @interface SysLog {

    /**
     * 操作内容
     */
    String value() default "";
}
