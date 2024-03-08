package cn.allbs.admin.entity.sys;


import java.time.LocalDateTime;

import cn.allbs.admin.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统角色表(sys_role)表实体类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统角色表", name = "sys_role")
@TableName("sys_role")
public class SysRoleEntity extends BaseEntity {

    @TableId(value = "role_id", type = IdType.AUTO)
    @Schema(description = "角色主键", name = "roleId", implementation = Integer.class)
    private Integer roleId;

    @Schema(description = "角色名称", name = "roleName", implementation = String.class)
    private String roleName;

    @Schema(description = "角色编码", name = "roleCode", implementation = String.class)
    private String roleCode;

    @Schema(description = "角色说明", name = "roleDesc", implementation = String.class)
    private String roleDesc;

    @Schema(description = "权限类型", name = "dsType", implementation = String.class)
    private String dsType;

    @Schema(description = "权限范围", name = "dsScope", implementation = String.class)
    private String dsScope;
}
