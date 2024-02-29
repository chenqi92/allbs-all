package cn.allbs.admin.controller.test;

import cn.allbs.admin.config.R;
import cn.allbs.admin.dto.test.TestFirDTO;
import cn.allbs.admin.entity.test.TestFirEntity;
import cn.allbs.admin.service.test.TestFirService;
import cn.allbs.admin.vo.test.TestFirVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (test_fir)表控制层
 *
 * @author chenqi
 * @since 2024-02-29 10:15:54
 */
@RestController
@AllArgsConstructor
@RequestMapping("/test/fir")
@Tag(name = "testFir", description = "测试表")
public class TestFirController {

    /**
     * 服务对象
     */
    private final TestFirService testFirService;

    /**
     * 分页查询所有信息
     *
     * @return List<TestFirDTO> 分页当前账户下所有信息
     */
    @Operation(description = "分页信息表数据", summary = "分页查询信息表数据", tags = {"分页查询所有数据"})
    @GetMapping("queryPage")
    @Parameters({@Parameter(description = "当前页", name = "current", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class)), @Parameter(description = "当前页条数", name = "size", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class))})
    public R<IPage<TestFirVO>> queryPage(@ParameterObject Page<TestFirDTO> page, @ParameterObject TestFirDTO testFirDTO) {
        return R.ok(this.testFirService.queryPage(page, testFirDTO));
    }

    /**
     * 查询所有信息
     *
     * @return List<TestFirEntity> 当前账户下所有信息
     */
    @Operation(description = "查询信息表数据", summary = "查询信息表数据", tags = {"查询所有数据"})
    @GetMapping("queryList")
    public R<List<TestFirVO>> queryList(@ParameterObject TestFirDTO testFirDTO) {
        return R.ok(this.testFirService.queryList(testFirDTO));
    }

    /**
     * 通过id查询
     *
     * @param id $pk.comment
     * @return R
     */
    @Operation(description = "通过id查询", summary = "通过id查询", tags = {"主键查询"})
    @GetMapping("/{id}")
    public R<TestFirEntity> getById(@PathVariable("id") Long id) {
        return R.ok(this.testFirService.getById(id));
    }

    /**
     * 新增
     *
     * @param testFirEntity
     * @return R
     */
    @Operation(description = "新增", summary = "新增", tags = {"数据新增"})
    @PostMapping
    public R<Boolean> save(@RequestBody TestFirEntity testFirEntity) {
        return R.ok(this.testFirService.save(testFirEntity));
    }

    /**
     * 修改
     *
     * @param testFirEntity
     * @return R
     */
    @Operation(description = "修改", summary = "修改", tags = {"数据更新"})
    @PutMapping
    public R<Boolean> updateById(@RequestBody TestFirEntity testFirEntity) {
        return R.ok(this.testFirService.updateById(testFirEntity));
    }

    /**
     * 通过id 删除
     *
     * @param id $pk.comment
     * @return R
     */
    @Operation(description = "通过id 删除", summary = "通过id 删除", tags = {"数据删除"})
    @DeleteMapping("/{id}")
    public R<Boolean> removeById(@PathVariable("id") Long id) {
        return R.ok(this.testFirService.removeById(id));
    }

}
