package cn.allbs.admin.config.function;

import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * 类 CheckedSupplier
 *
 * @author ChenQi
 * @date 2024/4/1
 */
@FunctionalInterface
public interface CheckedSupplier<T> extends Serializable {

    /**
     * Run the Supplier
     *
     * @return T
     * @throws Throwable CheckedException
     */
    @Nullable
    T get() throws Throwable;

}
