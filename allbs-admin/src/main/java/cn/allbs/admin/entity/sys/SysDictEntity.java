package cn.allbs.admin.entity.sys;


import cn.allbs.admin.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典表(sys_dict)表实体类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:05
 */
@Data
@Schema(description = "字典表", name = "sys_dict")
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict")
public class SysDictEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "编号", name = "id", implementation = Long.class)
    private Long id;

    @Schema(description = "类型", name = "type", implementation = String.class)
    private String type;

    @Schema(description = "描述", name = "description", implementation = String.class)
    private String description;

    @Schema(description = "备注", name = "remarks", implementation = String.class)
    private String remarks;
}
