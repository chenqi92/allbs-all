package cn.allbs.admin.config.dto;

import cn.allbs.admin.entity.sys.SysUserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 类 UserInfo 用户信息
 *
 * @author ChenQi
 * @date 2024/3/8
 */
@Data
@Schema(title = "用户信息", name = "UserInfo")
public class UserInfo implements Serializable {

    /**
     * 用户基本信息
     */
    @Schema(description = "用户基本信息", name = "sysUser", implementation = SysUserEntity.class)
    private SysUserEntity sysUser;
    /**
     * 权限标识集合
     */
    @Schema(description = "权限标识集合", name = "permissions")
    private String[] permissions;

    /**
     * 角色名称标识名称集合
     */
    @Schema(description = "角色名称标识集合", name = "roleName")
    private String[] roleName;

    /**
     * 用户关联的部门列表
     */
    @Schema(description = "用户关联的部门id", name = "deptIds")
    private Set<Long> deptIds;
}
