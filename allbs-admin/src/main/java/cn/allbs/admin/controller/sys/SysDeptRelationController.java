package cn.allbs.admin.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.allbs.admin.config.core.R;
import cn.allbs.admin.config.annotation.SysLog;
import cn.allbs.admin.entity.sys.SysDeptRelationEntity;
import cn.allbs.admin.dto.sys.SysDeptRelationDTO;
import cn.allbs.admin.vo.sys.SysDeptRelationVO;
import cn.allbs.admin.service.sys.SysDeptRelationService;
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
 * 部门关系表(sys_dept_relation)表控制层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:04
 */
@Hidden
@RestController
@AllArgsConstructor
@RequestMapping("/sys/dept/relation")
@Tag(name = "sysDeptRelation", description = "部门关系表 管理")
public class SysDeptRelationController {

    /**
     * 服务对象
     */
    private final SysDeptRelationService sysDeptRelationService;

    /**
     * 分页查询所有部门关系表信息
     *
     * @return List<SysDeptRelationDTO> 分页当前账户下所有部门关系表信息
     */
    @Operation(description = "分页部门关系表信息表数据", summary = "分页查询部门关系表信息表数据", tags = {"分页查询所有数据"})
    @GetMapping("queryPage")
    @Parameters({@Parameter(description = "当前页", name = "current", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class)), @Parameter(description = "当前页条数", name = "size", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class))})
    public R<IPage<SysDeptRelationVO>> queryPage(@ParameterObject Page<SysDeptRelationDTO> page, @ParameterObject SysDeptRelationDTO sysDeptRelationDTO) {
        return R.ok(this.sysDeptRelationService.queryPage(page, sysDeptRelationDTO));
    }

    /**
     * 查询所有部门关系表信息
     *
     * @return List<SysDeptRelationEntity> 当前账户下所有部门关系表信息
     */
    @Operation(description = "查询部门关系表信息表数据", summary = "查询部门关系表信息表数据", tags = {"查询所有数据"})
    @GetMapping("queryList")
    public R<List<SysDeptRelationVO>> queryList(@ParameterObject SysDeptRelationDTO sysDeptRelationDTO) {
        return R.ok(this.sysDeptRelationService.queryList(sysDeptRelationDTO));
    }

    /**
     * 通过id查询 部门关系表
     *
     * @param ancestor 祖先节点
     * @return R
     */
    @Operation(description = "通过ancestor查询", summary = "通过ancestor查询", tags = {"主键查询"})
    @GetMapping("/{ancestor}")
    @PreAuthorize("@pms.hasPermission('sysDeptRelation_list')")
    public R<SysDeptRelationEntity> getById(@PathVariable("ancestor") Integer ancestor) {
        return R.ok(this.sysDeptRelationService.getById(ancestor));
    }

    /**
     * 新增 部门关系表
     *
     * @param sysDeptRelationEntity 部门关系表
     * @return R
     */
    @Operation(description = "新增部门关系表", summary = "新增部门关系表", tags = {"数据新增"})
    @SysLog("新增部门关系表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sysDeptRelation_save')")
    public R<Boolean> save(@RequestBody SysDeptRelationEntity sysDeptRelationEntity) {
        return R.ok(this.sysDeptRelationService.save(sysDeptRelationEntity));
    }

    /**
     * 修改部门关系表
     *
     * @param sysDeptRelationEntity 部门关系表
     * @return R
     */
    @Operation(description = "修改部门关系表", summary = "修改部门关系表", tags = {"数据更新"})
    @SysLog("修改部门关系表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sysDeptRelation_update')")
    public R<Boolean> updateById(@RequestBody SysDeptRelationEntity sysDeptRelationEntity) {
        return R.ok(this.sysDeptRelationService.updateById(sysDeptRelationEntity));
    }

    /**
     * 通过ancestor 删除部门关系表
     *
     * @param ancestor 祖先节点
     * @return R
     */
    @PreAuthorize("@pms.hasPermission('sysDeptRelation_delete')")
    @Operation(description = "通过ancestor 删除部门关系表", summary = "通过ancestor 删除部门关系表", tags = {"数据删除"})
    @SysLog("通过id删除部门关系表")
    @DeleteMapping("/{ancestor}")
    public R<Boolean> removeById(@PathVariable("ancestor") Integer ancestor) {
        return R.ok(this.sysDeptRelationService.removeById(ancestor));
    }

}
