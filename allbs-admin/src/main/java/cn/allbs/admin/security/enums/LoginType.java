package cn.allbs.admin.security.enums;

import lombok.Getter;

/**
 * 枚举 LoginType 登录类型
 *
 * @author ChenQi
 * @date 2024/4/28
 */
@Getter
public enum LoginType {
    /**
     * 用户名
     */
    USERNAME("username"),
    /**
     * 手机
     */
    MOBILE("mobile"),
    /**
     * 邮箱
     */
    EMAIL("email"),
    /**
     * 微信小程序
     */
    MINI_PROGRAM("mini_program"); // 小程序登录，预留位置

    private final String type;

    LoginType(String type) {
        this.type = type;
    }

    public static LoginType fromType(String type) {
        for (LoginType loginType : values()) {
            if (loginType.getType().equals(type)) {
                return loginType;
            }
        }
        throw new IllegalArgumentException("未知的登录类型: " + type);
    }
}
