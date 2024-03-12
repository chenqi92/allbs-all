package cn.allbs.admin.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

/**
 * 类 AuthorizationException
 *
 * @author ChenQi
 * @date 2024/3/12
 */
@Getter
@JsonSerialize(using = AuthorizationExceptionSerializer.class)
public class AuthorizationException extends AuthenticationException {

    private int errorCode;

    public AuthorizationException(String msg) {
        super(msg);
    }

    public AuthorizationException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthorizationException(String msg, int errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
}

