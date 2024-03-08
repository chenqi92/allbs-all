package cn.allbs.admin.entity.sys;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色菜单表(sys_role_menu)表实体类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:09
 */
@Data
@Schema(description = "角色菜单表", name = "sys_role_menu")
@TableName("sys_role_menu")
public class SysRoleMenuEntity {

    @TableId(value = "role_id", type = IdType.AUTO)
    @Schema(description = "角色ID", name = "roleId", implementation = Integer.class)
    private Integer roleId;

    @Schema(description = "菜单ID", name = "menuId", implementation = Integer.class)
    private Integer menuId;
}
