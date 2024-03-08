package cn.allbs.admin.dto.sys;


import java.time.LocalDateTime;

import cn.allbs.admin.entity.sys.SysDictEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典表(sys_dict)DTO
 *
 * @author chenqi
 * @since 2024-03-08 17:39:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "字典表 参数接收类", name = "SysDictDTO")
public class SysDictDTO extends SysDictEntity {
}
