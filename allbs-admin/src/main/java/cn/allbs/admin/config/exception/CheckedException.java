package cn.allbs.admin.config.exception;

import lombok.NoArgsConstructor;

/**
 * 类 CheckedException
 *
 * @author ChenQi
 * @date 2024/4/1
 */
@NoArgsConstructor
public class CheckedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CheckedException(String message) {
        super(message);
    }

    public CheckedException(Throwable cause) {
        super(cause);
    }

    public CheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
