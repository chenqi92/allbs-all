package cn.allbs.admin.vo.sys;


import java.time.LocalDateTime;

import cn.allbs.admin.entity.sys.SysLogEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日志表(sys_log)VO
 *
 * @author chenqi
 * @since 2024-03-08 17:39:07
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "日志表 数据展示类", name = "SysLogVO")
public class SysLogVO extends SysLogEntity {
}
