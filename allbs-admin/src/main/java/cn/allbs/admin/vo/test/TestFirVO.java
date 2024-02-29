package cn.allbs.admin.vo.test;


import cn.allbs.admin.entity.test.TestFirEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (test_fir)VO
 *
 * @author chenqi
 * @since 2024-02-29 10:16:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = " 数据展示类", name = "TestFirVO")
public class TestFirVO extends TestFirEntity {
}
