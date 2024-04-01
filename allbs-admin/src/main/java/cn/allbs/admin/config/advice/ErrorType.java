package cn.allbs.admin.config.advice;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

/**
 * 类 ErrorType
 *
 * @author ChenQi
 * @date 2024/4/1
 */
@Getter
@RequiredArgsConstructor
public enum ErrorType {

    /**
     * rest 请求异常
     */
    REQUEST("request"),
    /**
     * 异步异常
     */
    ASYNC("async"),
    /**
     * 定时任务异常
     */
    SCHEDULER("scheduler"),
    /**
     * websocket 异常
     */
    WEB_SOCKET("websocket"),
    /**
     * 其他异常
     */
    OTHER("other");

    @JsonValue
    private final String type;

    @Nullable
    @JsonCreator
    public static ErrorType of(String type) {
        ErrorType[] values = ErrorType.values();
        for (ErrorType errorType : values) {
            if (errorType.type.equals(type)) {
                return errorType;
            }
        }
        return null;
    }
}
