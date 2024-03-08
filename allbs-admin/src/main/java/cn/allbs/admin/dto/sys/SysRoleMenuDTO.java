package cn.allbs.admin.dto.sys;


import cn.allbs.admin.entity.sys.SysRoleMenuEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色菜单表(sys_role_menu)DTO
 *
 * @author chenqi
 * @since 2024-03-08 17:39:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "角色菜单表 参数接收类", name = "SysRoleMenuDTO")
public class SysRoleMenuDTO extends SysRoleMenuEntity {
}
