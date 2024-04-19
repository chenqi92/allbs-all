package cn.allbs.admin.config.log.listener;

import cn.allbs.admin.config.log.aop.SysLogEvent;
import cn.allbs.admin.config.log.dto.LogDTO;
import cn.allbs.admin.config.utils.ConvertUtil;
import cn.allbs.admin.entity.sys.SysLogEntity;
import cn.allbs.admin.service.sys.SysLogService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 类 SysLogListener
 *
 * @author ChenQi
 * @date 2024/4/19
 */
@AllArgsConstructor
@Component
public class SysLogListener {

    private final SysLogService sysLogService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLogEntity sysLog = new SysLogEntity();
        LogDTO logDTO = event.getLogDTO();
        sysLog.setMethod(logDTO.getMethod());
        sysLog.setException(logDTO.getException());
        sysLog.setType(logDTO.getType());
        sysLog.setTitle(logDTO.getTitle());
        sysLog.setRemoteAddr(logDTO.getRemoteAddr());
        sysLog.setUserAgent(logDTO.getUserAgent());
        sysLog.setRequestUri(logDTO.getRequestUri());
        sysLog.setParams(event.getLogDTO().getParams());
        sysLog.setTime(ConvertUtil.convertObject(logDTO.getTime(), String.class, "0"));
        sysLog.setCreateTime(LocalDateTime.now());
        sysLog.setCreateUser(logDTO.getUserName());
        sysLogService.save(sysLog);
    }
}
