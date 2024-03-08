package cn.allbs.admin.dto.sys;


import cn.allbs.admin.entity.sys.SysDeptRelationEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门关系表(sys_dept_relation)DTO
 *
 * @author chenqi
 * @since 2024-03-08 17:39:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "部门关系表 参数接收类", name = "SysDeptRelationDTO")
public class SysDeptRelationDTO extends SysDeptRelationEntity {
}
