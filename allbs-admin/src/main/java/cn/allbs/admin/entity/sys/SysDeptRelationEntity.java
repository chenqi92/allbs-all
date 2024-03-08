package cn.allbs.admin.entity.sys;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 部门关系表(sys_dept_relation)表实体类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:05
 */
@Data
@Schema(description = "部门关系表", name = "sys_dept_relation")
@TableName("sys_dept_relation")
public class SysDeptRelationEntity {

    @TableId(value = "ancestor", type = IdType.AUTO)
    @Schema(description = "祖先节点", name = "ancestor", implementation = Integer.class)
    private Integer ancestor;

    @Schema(description = "后代节点", name = "descendant", implementation = Integer.class)
    private Integer descendant;
}
