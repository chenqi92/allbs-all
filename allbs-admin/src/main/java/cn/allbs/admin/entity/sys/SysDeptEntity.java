package cn.allbs.admin.entity.sys;


import cn.allbs.admin.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门管理(sys_dept)表实体类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "部门管理", name = "sys_dept")
@TableName("sys_dept")
public class SysDeptEntity extends BaseEntity {

    @TableId(value = "dept_id", type = IdType.AUTO)
    @Schema(description = "部门id", name = "deptId", implementation = Long.class)
    private Long deptId;

    @Schema(description = "部门名称", name = "name", implementation = String.class)
    private String name;

    @Schema(description = "排序", name = "sort", implementation = Integer.class)
    private Integer sort;

    @Schema(description = "父节点id", name = "parentId", implementation = Long.class)
    private Long parentId;
}
