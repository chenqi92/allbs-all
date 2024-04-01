package cn.allbs.admin.config.utils;

import cn.allbs.admin.config.advice.AllbsErrorEvent;
import cn.allbs.admin.config.exception.Exceptions;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

/**
 * 类 ErrorUtil
 *
 * @author ChenQi
 * @date 2024/4/1
 */
public class ErrorUtil {

    /**
     * 初始化异常信息
     *
     * @param error 异常
     * @param event 异常事件封装
     */
    public static void initErrorInfo(Throwable error, AllbsErrorEvent event) {
        // 堆栈信息
        event.setStackTrace(Exceptions.getStackTraceAsString(error));
        event.setExceptionName(error.getClass().getName());
        event.setMessage(error.getMessage());
        event.setCreatedAt(LocalDateTime.now());
        StackTraceElement[] elements = error.getStackTrace();
        if (!ObjectUtils.isEmpty(elements)) {
            // 报错的类信息
            StackTraceElement element = elements[0];
            event.setClassName(element.getClassName());
            event.setFileName(element.getFileName());
            event.setMethodName(element.getMethodName());
            event.setLineNumber(element.getLineNumber());
        }
    }
}

