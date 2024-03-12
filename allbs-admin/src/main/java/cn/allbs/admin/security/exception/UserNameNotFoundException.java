package cn.allbs.admin.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import static cn.allbs.admin.security.enums.SystemCode.USER_NOT_FOUND_ERROR;

/**
 * 类 UserNameNotFoundException
 *
 * @author ChenQi
 * @date 2024/3/12
 */
@JsonSerialize(using = AuthorizationExceptionSerializer.class)
public class UserNameNotFoundException extends AuthorizationException {

    public UserNameNotFoundException(String msg, Throwable t) {
        super(msg, USER_NOT_FOUND_ERROR.getCode());
    }

    public UserNameNotFoundException(String msg) {
        super(msg, USER_NOT_FOUND_ERROR.getCode());
    }

    public UserNameNotFoundException() {
        super(USER_NOT_FOUND_ERROR.getMsg(), USER_NOT_FOUND_ERROR.getCode());
    }
}
