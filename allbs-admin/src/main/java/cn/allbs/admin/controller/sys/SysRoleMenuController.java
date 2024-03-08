package cn.allbs.admin.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.allbs.admin.config.core.R;
import cn.allbs.admin.config.annotation.SysLog;
import cn.allbs.admin.entity.sys.SysRoleMenuEntity;
import cn.allbs.admin.dto.sys.SysRoleMenuDTO;
import cn.allbs.admin.vo.sys.SysRoleMenuVO;
import cn.allbs.admin.service.sys.SysRoleMenuService;
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
 * 角色菜单表(sys_role_menu)表控制层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:08
 */
@Hidden
@RestController
@AllArgsConstructor
@RequestMapping("/sys/role/menu")
@Tag(name = "sysRoleMenu", description = "角色菜单表 管理")
public class SysRoleMenuController {

    /**
     * 服务对象
     */
    private final SysRoleMenuService sysRoleMenuService;

    /**
     * 分页查询所有角色菜单表信息
     *
     * @return List<SysRoleMenuDTO> 分页当前账户下所有角色菜单表信息
     */
    @Operation(description = "分页角色菜单表信息表数据", summary = "分页查询角色菜单表信息表数据", tags = {"分页查询所有数据"})
    @GetMapping("queryPage")
    @Parameters({@Parameter(description = "当前页", name = "current", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class)), @Parameter(description = "当前页条数", name = "size", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class))})
    public R<IPage<SysRoleMenuVO>> queryPage(@ParameterObject Page<SysRoleMenuDTO> page, @ParameterObject SysRoleMenuDTO sysRoleMenuDTO) {
        return R.ok(this.sysRoleMenuService.queryPage(page, sysRoleMenuDTO));
    }

    /**
     * 查询所有角色菜单表信息
     *
     * @return List<SysRoleMenuEntity> 当前账户下所有角色菜单表信息
     */
    @Operation(description = "查询角色菜单表信息表数据", summary = "查询角色菜单表信息表数据", tags = {"查询所有数据"})
    @GetMapping("queryList")
    public R<List<SysRoleMenuVO>> queryList(@ParameterObject SysRoleMenuDTO sysRoleMenuDTO) {
        return R.ok(this.sysRoleMenuService.queryList(sysRoleMenuDTO));
    }

    /**
     * 通过id查询 角色菜单表
     *
     * @param roleId 角色ID
     * @return R
     */
    @Operation(description = "通过roleId查询", summary = "通过roleId查询", tags = {"主键查询"})
    @GetMapping("/{roleId}")
    @PreAuthorize("@pms.hasPermission('sysRoleMenu_list')")
    public R<SysRoleMenuEntity> getById(@PathVariable("roleId") Integer roleId) {
        return R.ok(this.sysRoleMenuService.getById(roleId));
    }

    /**
     * 新增 角色菜单表
     *
     * @param sysRoleMenuEntity 角色菜单表
     * @return R
     */
    @Operation(description = "新增角色菜单表", summary = "新增角色菜单表", tags = {"数据新增"})
    @SysLog("新增角色菜单表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sysRoleMenu_save')")
    public R<Boolean> save(@RequestBody SysRoleMenuEntity sysRoleMenuEntity) {
        return R.ok(this.sysRoleMenuService.save(sysRoleMenuEntity));
    }

    /**
     * 修改角色菜单表
     *
     * @param sysRoleMenuEntity 角色菜单表
     * @return R
     */
    @Operation(description = "修改角色菜单表", summary = "修改角色菜单表", tags = {"数据更新"})
    @SysLog("修改角色菜单表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sysRoleMenu_update')")
    public R<Boolean> updateById(@RequestBody SysRoleMenuEntity sysRoleMenuEntity) {
        return R.ok(this.sysRoleMenuService.updateById(sysRoleMenuEntity));
    }

    /**
     * 通过roleId 删除角色菜单表
     *
     * @param roleId 角色ID
     * @return R
     */
    @PreAuthorize("@pms.hasPermission('sysRoleMenu_delete')")
    @Operation(description = "通过roleId 删除角色菜单表", summary = "通过roleId 删除角色菜单表", tags = {"数据删除"})
    @SysLog("通过id删除角色菜单表")
    @DeleteMapping("/{roleId}")
    public R<Boolean> removeById(@PathVariable("roleId") Integer roleId) {
        return R.ok(this.sysRoleMenuService.removeById(roleId));
    }

}
