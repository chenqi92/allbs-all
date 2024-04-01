package cn.allbs.admin.config.utils.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 类 BeanDiff
 *
 * @author ChenQi
 * @date 2024/4/1
 */
@Getter
public class BeanDiff implements Serializable {
    /**
     * 变更字段
     */
    @JsonIgnore
    private final transient Set<String> fields = new HashSet<>();
    /**
     * 旧值
     */
    @JsonIgnore
    private final transient Map<String, Object> oldValues = new HashMap<>();
    /**
     * 新值
     */
    @JsonIgnore
    private final transient Map<String, Object> newValues = new HashMap<>();
}
