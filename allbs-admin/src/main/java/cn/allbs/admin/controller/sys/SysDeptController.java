package cn.allbs.admin.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.allbs.admin.config.core.R;
import cn.allbs.admin.config.annotation.SysLog;
import cn.allbs.admin.entity.sys.SysDeptEntity;
import cn.allbs.admin.dto.sys.SysDeptDTO;
import cn.allbs.admin.vo.sys.SysDeptVO;
import cn.allbs.admin.service.sys.SysDeptService;
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
 * 部门管理(sys_dept)表控制层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:04
 */
@Hidden
@RestController
@AllArgsConstructor
@RequestMapping("/sys/dept")
@Tag(name = "sysDept", description = "部门管理 管理")
public class SysDeptController {

    /**
     * 服务对象
     */
    private final SysDeptService sysDeptService;

    /**
     * 分页查询所有部门管理信息
     *
     * @return List<SysDeptDTO> 分页当前账户下所有部门管理信息
     */
    @Operation(description = "分页部门管理信息表数据", summary = "分页查询部门管理信息表数据", tags = {"分页查询所有数据"})
    @GetMapping("queryPage")
    @Parameters({@Parameter(description = "当前页", name = "current", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class)), @Parameter(description = "当前页条数", name = "size", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class))})
    public R<IPage<SysDeptVO>> queryPage(@ParameterObject Page<SysDeptDTO> page, @ParameterObject SysDeptDTO sysDeptDTO) {
        return R.ok(this.sysDeptService.queryPage(page, sysDeptDTO));
    }

    /**
     * 查询所有部门管理信息
     *
     * @return List<SysDeptEntity> 当前账户下所有部门管理信息
     */
    @Operation(description = "查询部门管理信息表数据", summary = "查询部门管理信息表数据", tags = {"查询所有数据"})
    @GetMapping("queryList")
    public R<List<SysDeptVO>> queryList(@ParameterObject SysDeptDTO sysDeptDTO) {
        return R.ok(this.sysDeptService.queryList(sysDeptDTO));
    }

    /**
     * 通过id查询 部门管理
     *
     * @param deptId 部门id
     * @return R
     */
    @Operation(description = "通过deptId查询", summary = "通过deptId查询", tags = {"主键查询"})
    @GetMapping("/{deptId}")
    @PreAuthorize("@pms.hasPermission('sysDept_list')")
    public R<SysDeptEntity> getById(@PathVariable("deptId") Long deptId) {
        return R.ok(this.sysDeptService.getById(deptId));
    }

    /**
     * 新增 部门管理
     *
     * @param sysDeptEntity 部门管理
     * @return R
     */
    @Operation(description = "新增部门管理", summary = "新增部门管理", tags = {"数据新增"})
    @SysLog("新增部门管理")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sysDept_save')")
    public R<Boolean> save(@RequestBody SysDeptEntity sysDeptEntity) {
        return R.ok(this.sysDeptService.save(sysDeptEntity));
    }

    /**
     * 修改部门管理
     *
     * @param sysDeptEntity 部门管理
     * @return R
     */
    @Operation(description = "修改部门管理", summary = "修改部门管理", tags = {"数据更新"})
    @SysLog("修改部门管理")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sysDept_update')")
    public R<Boolean> updateById(@RequestBody SysDeptEntity sysDeptEntity) {
        return R.ok(this.sysDeptService.updateById(sysDeptEntity));
    }

    /**
     * 通过deptId 删除部门管理
     *
     * @param deptId 部门id
     * @return R
     */
    @PreAuthorize("@pms.hasPermission('sysDept_delete')")
    @Operation(description = "通过deptId 删除部门管理", summary = "通过deptId 删除部门管理", tags = {"数据删除"})
    @SysLog("通过id删除部门管理")
    @DeleteMapping("/{deptId}")
    public R<Boolean> removeById(@PathVariable("deptId") Long deptId) {
        return R.ok(this.sysDeptService.removeById(deptId));
    }

}
