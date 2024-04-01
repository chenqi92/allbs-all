package cn.allbs.admin.config.function;

import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * 类 CheckedFunction
 *
 * @author ChenQi
 * @date 2024/4/1
 */
@FunctionalInterface
public interface CheckedFunction<T, R> extends Serializable {

    /**
     * Run the Function
     *
     * @param t T
     * @return R R
     * @throws Throwable CheckedException
     */
    @Nullable
    R apply(@Nullable T t) throws Throwable;

}
