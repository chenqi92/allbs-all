package cn.allbs.admin.entity.sys;


import cn.allbs.admin.config.log.dto.LogDTO;
import cn.allbs.admin.config.utils.ConvertUtil;
import cn.allbs.admin.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 日志表(sys_log)表实体类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "日志表", name = "sys_log")
@TableName("sys_log")
public class SysLogEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "日志主键", name = "id", implementation = Long.class)
    private Long id;

    @Schema(description = "日志类型0 登录日志  1系统日志", name = "type", implementation = String.class)
    private String type;

    @Schema(description = "操作说明", name = "title", implementation = String.class)
    private String title;

    @Schema(description = "产生日志的服务编码", name = "serviceId", implementation = String.class)
    private String serviceId;

    @Schema(description = "执行操作的ip地址", name = "remoteAddr", implementation = String.class)
    private String remoteAddr;

    @Schema(description = "代理地址", name = "userAgent", implementation = String.class)
    private String userAgent;

    @Schema(description = "请求地址", name = "requestUri", implementation = String.class)
    private String requestUri;

    @Schema(description = "执行方法", name = "method", implementation = String.class)
    private String method;

    @Schema(description = "查询参数", name = "params", implementation = String.class)
    private String params;

    @Schema(description = "执行耗时", name = "time", implementation = String.class)
    private String time;

    @Schema(description = "异常说明", name = "exception", implementation = String.class)
    private String exception;
}
