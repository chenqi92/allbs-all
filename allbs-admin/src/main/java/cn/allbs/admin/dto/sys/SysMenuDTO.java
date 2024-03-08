package cn.allbs.admin.dto.sys;


import java.time.LocalDateTime;

import cn.allbs.admin.entity.sys.SysMenuEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单权限表(sys_menu)DTO
 *
 * @author chenqi
 * @since 2024-03-08 17:39:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "菜单权限表 参数接收类", name = "SysMenuDTO")
public class SysMenuDTO extends SysMenuEntity {
}
