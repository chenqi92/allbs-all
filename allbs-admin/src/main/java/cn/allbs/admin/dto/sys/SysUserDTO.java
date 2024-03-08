package cn.allbs.admin.dto.sys;


import java.time.LocalDateTime;

import cn.allbs.admin.entity.sys.SysUserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户表(sys_user)DTO
 *
 * @author chenqi
 * @since 2024-03-08 17:39:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "用户表 参数接收类", name = "SysUserDTO")
public class SysUserDTO extends SysUserEntity {
}
