package cn.allbs.admin.config.function;

import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * 类 CheckedCallable
 *
 * @author ChenQi
 * @date 2024/4/1
 */
@FunctionalInterface
public interface CheckedCallable<T> extends Serializable {

    /**
     * Run this callable.
     *
     * @return result
     * @throws Throwable CheckedException
     */
    @Nullable
    T call() throws Throwable;
}
