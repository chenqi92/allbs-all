package cn.allbs.admin.config.function;

import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * 类 CheckedConsumer
 */
@FunctionalInterface
public interface CheckedConsumer<T> extends Serializable {

    /**
     * Run the Consumer
     *
     * @param t T
     * @throws Throwable UncheckedException
     */
    void accept(@Nullable T t) throws Throwable;

}
