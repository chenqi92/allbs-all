package cn.allbs.admin.security.model;

import cn.allbs.admin.security.enums.LoginType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * 登录请求类实现接口。添加loginType使系统能够正确的多态序列化和反序列化
 *
 * @author ChenQi
 * @date 2024/4/28
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "loginType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserNameLoginRequest.class, name = "username"),
        @JsonSubTypes.Type(value = EmailLoginRequest.class, name = "email"),
        @JsonSubTypes.Type(value = MobileLoginRequest.class, name = "mobile"),
        @JsonSubTypes.Type(value = MiniProgramLoginRequest.class, name = "miniProgram"),
})
public interface LoginRequest {
    /**
     * 获取登录类型
     *
     * @return 登录类型
     */
    LoginType getLoginType();
}
