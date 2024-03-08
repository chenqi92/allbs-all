package cn.allbs.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 类 BaseEntity
 *
 * @author ChenQi
 * @date 2024/3/8
 */
@Data
@Schema(description = "审计字段", name = "BaseEntity")
public class BaseEntity {

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "逻辑删除", name = "delFlg", implementation = Integer.class)
    private Integer delFlg;

    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建时间", name = "createTime", implementation = LocalDateTime.class)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新时间", name = "updateTime", implementation = LocalDateTime.class)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建者", name = "createUser", implementation = String.class)
    private String createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新者", name = "updateUser", implementation = String.class)
    private String updateUser;
}
