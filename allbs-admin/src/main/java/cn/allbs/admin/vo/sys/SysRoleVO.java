package cn.allbs.admin.vo.sys;


import java.time.LocalDateTime;

import cn.allbs.admin.entity.sys.SysRoleEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统角色表(sys_role)VO
 *
 * @author chenqi
 * @since 2024-03-08 17:39:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "系统角色表 数据展示类", name = "SysRoleVO")
public class SysRoleVO extends SysRoleEntity {
}
