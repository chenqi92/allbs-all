package cn.allbs.admin.config.log.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 类 SysLogDTO
 *
 * @author ChenQi
 * @date 2024/4/19
 */
@Data
public class LogDTO {

    @Schema(description = "类型", name = "type", implementation = String.class)
    private String type;

    @Schema(description = "方法注释", name = "title", implementation = String.class)
    private String title;

    @Schema(description = "远程ip", name = "remoteAddr", implementation = String.class)
    private String remoteAddr;

    @Schema(description = "用户代理", name = "userAgent", implementation = String.class)
    private String userAgent;

    @Schema(description = "请求的接口地址", name = "requestUri", implementation = String.class)
    private String requestUri;

    @Schema(description = "执行方法", name = "method", implementation = String.class)
    private String method;

    @Schema(description = "参数", name = "params", implementation = String.class)
    private String params;

    @Schema(description = "执行时间", name = "time", implementation = Object.class)
    private Object time;

    @Schema(description = "异常说明", name = "exception", implementation = String.class)
    private String exception;

    @Schema(description = "用户名", name = "userName", implementation = String.class)
    private String userName;
}
