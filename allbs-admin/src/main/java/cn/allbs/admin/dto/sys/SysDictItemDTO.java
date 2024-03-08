package cn.allbs.admin.dto.sys;


import java.time.LocalDateTime;

import cn.allbs.admin.entity.sys.SysDictItemEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典项(sys_dict_item)DTO
 *
 * @author chenqi
 * @since 2024-03-08 17:39:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "字典项 参数接收类", name = "SysDictItemDTO")
public class SysDictItemDTO extends SysDictItemEntity {
}
