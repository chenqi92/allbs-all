package cn.allbs.admin.entity.sys;


import cn.allbs.admin.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户表(sys_user)表实体类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户表", name = "sys_user")
@TableName("sys_user")
public class SysUserEntity extends BaseEntity {

    @TableId(value = "user_id", type = IdType.AUTO)
    @Schema(description = "主键ID", name = "userId", implementation = Long.class)
    private Long userId;

    @Schema(description = "用户名", name = "username", implementation = String.class)
    private String username;

    @Schema(description = "昵称", name = "nickname", implementation = String.class)
    private String nickname;

    @Schema(description = "密码", name = "password", implementation = String.class)
    private String password;

    @Schema(description = "盐值", name = "salt", implementation = String.class)
    private String salt;

    @Schema(description = "手机号", name = "phone", implementation = String.class)
    private String phone;

    @Schema(description = "头像", name = "avatar", implementation = String.class)
    private String avatar;

    @Schema(description = "部门ID", name = "deptId", implementation = Long.class)
    private Long deptId;

    @Schema(description = "是否锁定 0正常 9冻结", name = "lockFlg", implementation = Integer.class)
    private Integer lockFlg;

    @Schema(description = "微信登录openId", name = "wxOpenid", implementation = String.class)
    private String wxOpenid;

    @Schema(description = "小程序openId", name = "miniOpenid", implementation = String.class)
    private String miniOpenid;

    @Schema(description = "QQ openId", name = "qqOpenid", implementation = String.class)
    private String qqOpenid;

    @Schema(description = "码云 标识", name = "giteeLogin", implementation = String.class)
    private String giteeLogin;
}
