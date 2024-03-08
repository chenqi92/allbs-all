package cn.allbs.admin.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.allbs.admin.config.core.R;
import cn.allbs.admin.config.annotation.SysLog;
import cn.allbs.admin.entity.sys.SysDictEntity;
import cn.allbs.admin.dto.sys.SysDictDTO;
import cn.allbs.admin.vo.sys.SysDictVO;
import cn.allbs.admin.service.sys.SysDictService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * 字典表(sys_dict)表控制层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:05
 */
@Hidden
@RestController
@AllArgsConstructor
@RequestMapping("/sys/dict")
@Tag(name = "sysDict", description = "字典表 管理")
public class SysDictController {

    /**
     * 服务对象
     */
    private final SysDictService sysDictService;

    /**
     * 分页查询所有字典表信息
     *
     * @return List<SysDictDTO> 分页当前账户下所有字典表信息
     */
    @Operation(description = "分页字典表信息表数据", summary = "分页查询字典表信息表数据", tags = {"分页查询所有数据"})
    @GetMapping("queryPage")
    @Parameters({@Parameter(description = "当前页", name = "current", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class)), @Parameter(description = "当前页条数", name = "size", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class))})
    public R<IPage<SysDictVO>> queryPage(@ParameterObject Page<SysDictDTO> page, @ParameterObject SysDictDTO sysDictDTO) {
        return R.ok(this.sysDictService.queryPage(page, sysDictDTO));
    }

    /**
     * 查询所有字典表信息
     *
     * @return List<SysDictEntity> 当前账户下所有字典表信息
     */
    @Operation(description = "查询字典表信息表数据", summary = "查询字典表信息表数据", tags = {"查询所有数据"})
    @GetMapping("queryList")
    public R<List<SysDictVO>> queryList(@ParameterObject SysDictDTO sysDictDTO) {
        return R.ok(this.sysDictService.queryList(sysDictDTO));
    }

    /**
     * 通过id查询 字典表
     *
     * @param id 编号
     * @return R
     */
    @Operation(description = "通过id查询", summary = "通过id查询", tags = {"主键查询"})
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sysDict_list')")
    public R<SysDictEntity> getById(@PathVariable("id") Long id) {
        return R.ok(this.sysDictService.getById(id));
    }

    /**
     * 新增 字典表
     *
     * @param sysDictEntity 字典表
     * @return R
     */
    @Operation(description = "新增字典表", summary = "新增字典表", tags = {"数据新增"})
    @SysLog("新增字典表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sysDict_save')")
    public R<Boolean> save(@RequestBody SysDictEntity sysDictEntity) {
        return R.ok(this.sysDictService.save(sysDictEntity));
    }

    /**
     * 修改字典表
     *
     * @param sysDictEntity 字典表
     * @return R
     */
    @Operation(description = "修改字典表", summary = "修改字典表", tags = {"数据更新"})
    @SysLog("修改字典表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sysDict_update')")
    public R<Boolean> updateById(@RequestBody SysDictEntity sysDictEntity) {
        return R.ok(this.sysDictService.updateById(sysDictEntity));
    }

    /**
     * 通过id 删除字典表
     *
     * @param id 编号
     * @return R
     */
    @PreAuthorize("@pms.hasPermission('sysDict_delete')")
    @Operation(description = "通过id 删除字典表", summary = "通过id 删除字典表", tags = {"数据删除"})
    @SysLog("通过id删除字典表")
    @DeleteMapping("/{id}")
    public R<Boolean> removeById(@PathVariable("id") Long id) {
        return R.ok(this.sysDictService.removeById(id));
    }

}
