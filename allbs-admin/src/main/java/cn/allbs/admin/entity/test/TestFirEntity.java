package cn.allbs.admin.entity.test;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * (test_fir)表实体类
 *
 * @author chenqi
 * @since 2024-02-29 10:15:55
 */
@Data
@Schema(description = "", name = "test_fir")
@TableName("test_fir")
public class TestFirEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -65565604239459763L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "", name = "id", implementation = Long.class)
    private Long id;

    @Schema(description = "性别", name = "sex", implementation = Integer.class)
    private Integer sex;

    @Schema(description = "姓名", name = "name", implementation = String.class)
    private String name;

    @Schema(description = "", name = "code", implementation = String.class)
    private String code;
}
