package cn.allbs.admin.config.function;

import java.io.Serializable;

/**
 * 类 CheckedComparator
 *
 * @author ChenQi
 * @date 2024/4/1
 */
@FunctionalInterface
public interface CheckedComparator<T> extends Serializable {

    /**
     * Compares its two arguments for order.
     *
     * @param o1 o1
     * @param o2 o2
     * @return int
     * @throws Throwable CheckedException
     */
    int compare(T o1, T o2) throws Throwable;

}
