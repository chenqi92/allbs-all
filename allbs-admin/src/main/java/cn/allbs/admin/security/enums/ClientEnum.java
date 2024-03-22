package cn.allbs.admin.security.enums;

import cn.allbs.admin.config.enums.BasicEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 枚举 ClientEnum 客户端枚举
 *
 * @author ChenQi
 * @date 2024/3/22
 */
@Getter
@RequiredArgsConstructor
public enum ClientEnum implements BasicEnum<Integer, ClientEnum> {

    /**
     * 网页端
     */
    WEB(1, "web端"),

    /**
     * app端
     */
    APP(2, "app端"),

    /**
     * 小程序端
     */
    MINI_WECHAT(3, "微信小程序");

    /**
     * 属性值
     */
    private final Integer value;

    /**
     * 属性名称
     */
    private final String label;

    @Override
    public Integer getValue() {
        return null;
    }
}
