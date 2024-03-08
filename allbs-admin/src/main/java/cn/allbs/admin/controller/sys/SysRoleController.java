package cn.allbs.admin.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.allbs.admin.config.core.R;
import cn.allbs.admin.config.annotation.SysLog;
import cn.allbs.admin.entity.sys.SysRoleEntity;
import cn.allbs.admin.dto.sys.SysRoleDTO;
import cn.allbs.admin.vo.sys.SysRoleVO;
import cn.allbs.admin.service.sys.SysRoleService;
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
 * 系统角色表(sys_role)表控制层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:08
 */
@Hidden
@RestController
@AllArgsConstructor
@RequestMapping("/sys/role")
@Tag(name = "sysRole", description = "系统角色表 管理")
public class SysRoleController {

    /**
     * 服务对象
     */
    private final SysRoleService sysRoleService;

    /**
     * 分页查询所有系统角色表信息
     *
     * @return List<SysRoleDTO> 分页当前账户下所有系统角色表信息
     */
    @Operation(description = "分页系统角色表信息表数据", summary = "分页查询系统角色表信息表数据", tags = {"分页查询所有数据"})
    @GetMapping("queryPage")
    @Parameters({@Parameter(description = "当前页", name = "current", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class)), @Parameter(description = "当前页条数", name = "size", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class))})
    public R<IPage<SysRoleVO>> queryPage(@ParameterObject Page<SysRoleDTO> page, @ParameterObject SysRoleDTO sysRoleDTO) {
        return R.ok(this.sysRoleService.queryPage(page, sysRoleDTO));
    }

    /**
     * 查询所有系统角色表信息
     *
     * @return List<SysRoleEntity> 当前账户下所有系统角色表信息
     */
    @Operation(description = "查询系统角色表信息表数据", summary = "查询系统角色表信息表数据", tags = {"查询所有数据"})
    @GetMapping("queryList")
    public R<List<SysRoleVO>> queryList(@ParameterObject SysRoleDTO sysRoleDTO) {
        return R.ok(this.sysRoleService.queryList(sysRoleDTO));
    }

    /**
     * 通过id查询 系统角色表
     *
     * @param roleId 角色主键
     * @return R
     */
    @Operation(description = "通过roleId查询", summary = "通过roleId查询", tags = {"主键查询"})
    @GetMapping("/{roleId}")
    @PreAuthorize("@pms.hasPermission('sysRole_list')")
    public R<SysRoleEntity> getById(@PathVariable("roleId") Integer roleId) {
        return R.ok(this.sysRoleService.getById(roleId));
    }

    /**
     * 新增 系统角色表
     *
     * @param sysRoleEntity 系统角色表
     * @return R
     */
    @Operation(description = "新增系统角色表", summary = "新增系统角色表", tags = {"数据新增"})
    @SysLog("新增系统角色表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sysRole_save')")
    public R<Boolean> save(@RequestBody SysRoleEntity sysRoleEntity) {
        return R.ok(this.sysRoleService.save(sysRoleEntity));
    }

    /**
     * 修改系统角色表
     *
     * @param sysRoleEntity 系统角色表
     * @return R
     */
    @Operation(description = "修改系统角色表", summary = "修改系统角色表", tags = {"数据更新"})
    @SysLog("修改系统角色表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sysRole_update')")
    public R<Boolean> updateById(@RequestBody SysRoleEntity sysRoleEntity) {
        return R.ok(this.sysRoleService.updateById(sysRoleEntity));
    }

    /**
     * 通过roleId 删除系统角色表
     *
     * @param roleId 角色主键
     * @return R
     */
    @PreAuthorize("@pms.hasPermission('sysRole_delete')")
    @Operation(description = "通过roleId 删除系统角色表", summary = "通过roleId 删除系统角色表", tags = {"数据删除"})
    @SysLog("通过id删除系统角色表")
    @DeleteMapping("/{roleId}")
    public R<Boolean> removeById(@PathVariable("roleId") Integer roleId) {
        return R.ok(this.sysRoleService.removeById(roleId));
    }

}
