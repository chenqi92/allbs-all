package cn.allbs.admin.config.log.aop;

import cn.allbs.admin.config.log.dto.LogDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 类 SysLogEvent
 *
 * @author ChenQi
 * @date 2024/4/19
 */
@Getter
@AllArgsConstructor
public class SysLogEvent {

    private final LogDTO logDTO;
}

