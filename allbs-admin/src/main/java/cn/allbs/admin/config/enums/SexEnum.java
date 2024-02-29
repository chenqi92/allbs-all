package cn.allbs.admin.config.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举 SexEnum 性别枚举
 *
 * @author ChenQi
 * @date 2024/2/29
 */
@Getter
@AllArgsConstructor
public enum SexEnum implements BasicEnum<Integer, SexEnum> {

    /**
     * 男
     */
    MALE(0, "男"),

    /**
     * 女
     */
    FEMALE(1, "女");

    /**
     * 属性值
     */
    private final Integer value;

    /**
     * 属性名称
     */
    private final String label;
}
