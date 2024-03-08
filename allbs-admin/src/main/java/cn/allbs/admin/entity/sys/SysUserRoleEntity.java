package cn.allbs.admin.entity.sys;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户角色表(sys_user_role)表实体类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:10
 */
@Data
@Schema(description = "用户角色表", name = "sys_user_role")
@TableName("sys_user_role")
public class SysUserRoleEntity {

    @TableId(value = "user_id", type = IdType.AUTO)
    @Schema(description = "用户ID", name = "userId", implementation = Integer.class)
    private Integer userId;

    @Schema(description = "角色ID", name = "roleId", implementation = Integer.class)
    private Integer roleId;
}
