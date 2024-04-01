package cn.allbs.admin.config.utils.beans;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * 类 AllbsBeanMapKey
 *
 * @author ChenQi
 * @date 2024/4/1
 */
@EqualsAndHashCode
@RequiredArgsConstructor
public class AllbsBeanMapKey {
    private final Class type;
    private final int require;
}
