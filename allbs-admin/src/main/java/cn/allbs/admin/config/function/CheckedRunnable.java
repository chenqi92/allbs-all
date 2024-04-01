package cn.allbs.admin.config.function;

import java.io.Serializable;

/**
 * 类 CheckedRunnable
 *
 * @author ChenQi
 * @date 2024/4/1
 */
@FunctionalInterface
public interface CheckedRunnable extends Serializable {

    /**
     * Run this runnable.
     *
     * @throws Throwable CheckedException
     */
    void run() throws Throwable;

}
