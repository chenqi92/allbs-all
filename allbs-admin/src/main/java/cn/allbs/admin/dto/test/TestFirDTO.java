package cn.allbs.admin.dto.test;


import cn.allbs.admin.entity.test.TestFirEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (test_fir)DTO
 *
 * @author chenqi
 * @since 2024-02-29 10:16:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = " 参数接收类", name = "TestFirDTO")
public class TestFirDTO extends TestFirEntity {
}
