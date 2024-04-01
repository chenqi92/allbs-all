package cn.allbs.admin.config.utils.beans;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 类 MicaBeanCopierKey
 *
 * @author ChenQi
 * @date 2024/4/1
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class AllbsBeanCopierKey {
    private final Class<?> source;
    private final Class<?> target;
    private final boolean useConverter;
    private final boolean nonNull;
}
