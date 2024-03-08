package cn.allbs.admin.entity.sys;


import cn.allbs.admin.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单权限表(sys_menu)表实体类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "菜单权限表", name = "sys_menu")
@TableName("sys_menu")
public class SysMenuEntity extends BaseEntity {

    @TableId(value = "menu_id", type = IdType.AUTO)
    @Schema(description = "菜单ID", name = "menuId", implementation = Long.class)
    private Long menuId;

    @Schema(description = "菜单名称", name = "name", implementation = String.class)
    private String name;

    @Schema(description = "权限标识", name = "permission", implementation = String.class)
    private String permission;

    @Schema(description = "菜单路径", name = "path", implementation = String.class)
    private String path;

    @Schema(description = "父菜单ID", name = "parentId", implementation = Long.class)
    private Long parentId;

    @Schema(description = "菜单图标", name = "icon", implementation = String.class)
    private String icon;

    @Schema(description = "排序值", name = "sort", implementation = Integer.class)
    private Integer sort;

    @Schema(description = "是否保持存活", name = "keepAlive", implementation = Integer.class)
    private Integer keepAlive;

    @Schema(description = "菜单类型 0目录 1菜单 2按钮", name = "type", implementation = Integer.class)
    private Integer type;
}
