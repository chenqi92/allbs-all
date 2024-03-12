package cn.allbs.admin.security.exception;

import cn.allbs.admin.security.enums.SystemCode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 类 UnauthorizedException
 *
 * @author ChenQi
 * @date 2024/3/12
 */
@JsonSerialize(using = AuthorizationExceptionSerializer.class)
public class UnauthorizedException extends AuthorizationException {

    public UnauthorizedException(String msg, Throwable t) {
        super(msg, SystemCode.TOKEN_NOT_IN_SYSTEM.getCode());
    }

    public UnauthorizedException(String msg) {
        super(msg, SystemCode.TOKEN_NOT_IN_SYSTEM.getCode());
    }
}
