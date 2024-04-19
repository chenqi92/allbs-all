package cn.allbs.admin.config.log.aop;

import cn.allbs.admin.config.annotation.SysLog;
import cn.allbs.admin.config.log.dto.LogDTO;
import cn.allbs.admin.config.log.enums.LogTypeEnum;
import cn.allbs.admin.config.utils.ServletUtil;
import cn.allbs.admin.config.utils.URLUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 类 SysLogAspect
 *
 * @author ChenQi
 * @date 2024/4/19
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
@Component
public class SysLogAspect {

    private final ApplicationEventPublisher publisher;

    @SneakyThrows
    @Around("@annotation(sysLog)")
    public Object around(ProceedingJoinPoint point, SysLog sysLog) {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);

        LogDTO logVo = getSysLog();
        logVo.setTitle(sysLog.value());
        // 发送异步日志事件
        Long startTime = System.currentTimeMillis();
        Object obj;
        try {
            obj = point.proceed();
        } catch (Exception e) {
            logVo.setType(LogTypeEnum.ERROR.getType());
            logVo.setException(e.getMessage());
            throw e;
        } finally {
            Long endTime = System.currentTimeMillis();
            logVo.setTime(endTime - startTime);
            publisher.publishEvent(new SysLogEvent(logVo));
        }
        return obj;
    }

    private LogDTO getSysLog() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        LogDTO sysLog = new LogDTO();
        sysLog.setType(LogTypeEnum.NORMAL.getType());
        sysLog.setRemoteAddr(ServletUtil.getClientIP(request));
        sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
        sysLog.setMethod(request.getMethod());
        sysLog.setUserAgent(request.getHeader("user-agent"));
        Map<String, Object> params = request.getParameterMap().entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        v -> v.getValue()[0],
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        sysLog.setParams(URLUtil.buildQuery(params, Charset.defaultCharset()));
        return sysLog;
    }
}
