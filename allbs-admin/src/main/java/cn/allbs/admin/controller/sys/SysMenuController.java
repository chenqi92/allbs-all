package cn.allbs.admin.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.allbs.admin.config.core.R;
import cn.allbs.admin.config.annotation.SysLog;
import cn.allbs.admin.entity.sys.SysMenuEntity;
import cn.allbs.admin.dto.sys.SysMenuDTO;
import cn.allbs.admin.vo.sys.SysMenuVO;
import cn.allbs.admin.service.sys.SysMenuService;
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
 * 菜单权限表(sys_menu)表控制层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:07
 */
@Hidden
@RestController
@AllArgsConstructor
@RequestMapping("/sys/menu")
@Tag(name = "sysMenu", description = "菜单权限表 管理")
public class SysMenuController {

    /**
     * 服务对象
     */
    private final SysMenuService sysMenuService;

    /**
     * 分页查询所有菜单权限表信息
     *
     * @return List<SysMenuDTO> 分页当前账户下所有菜单权限表信息
     */
    @Operation(description = "分页菜单权限表信息表数据", summary = "分页查询菜单权限表信息表数据", tags = {"分页查询所有数据"})
    @GetMapping("queryPage")
    @Parameters({@Parameter(description = "当前页", name = "current", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class)), @Parameter(description = "当前页条数", name = "size", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class))})
    public R<IPage<SysMenuVO>> queryPage(@ParameterObject Page<SysMenuDTO> page, @ParameterObject SysMenuDTO sysMenuDTO) {
        return R.ok(this.sysMenuService.queryPage(page, sysMenuDTO));
    }

    /**
     * 查询所有菜单权限表信息
     *
     * @return List<SysMenuEntity> 当前账户下所有菜单权限表信息
     */
    @Operation(description = "查询菜单权限表信息表数据", summary = "查询菜单权限表信息表数据", tags = {"查询所有数据"})
    @GetMapping("queryList")
    public R<List<SysMenuVO>> queryList(@ParameterObject SysMenuDTO sysMenuDTO) {
        return R.ok(this.sysMenuService.queryList(sysMenuDTO));
    }

    /**
     * 通过id查询 菜单权限表
     *
     * @param menuId 菜单ID
     * @return R
     */
    @Operation(description = "通过menuId查询", summary = "通过menuId查询", tags = {"主键查询"})
    @GetMapping("/{menuId}")
    @PreAuthorize("@pms.hasPermission('sysMenu_list')")
    public R<SysMenuEntity> getById(@PathVariable("menuId") Long menuId) {
        return R.ok(this.sysMenuService.getById(menuId));
    }

    /**
     * 新增 菜单权限表
     *
     * @param sysMenuEntity 菜单权限表
     * @return R
     */
    @Operation(description = "新增菜单权限表", summary = "新增菜单权限表", tags = {"数据新增"})
    @SysLog("新增菜单权限表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sysMenu_save')")
    public R<Boolean> save(@RequestBody SysMenuEntity sysMenuEntity) {
        return R.ok(this.sysMenuService.save(sysMenuEntity));
    }

    /**
     * 修改菜单权限表
     *
     * @param sysMenuEntity 菜单权限表
     * @return R
     */
    @Operation(description = "修改菜单权限表", summary = "修改菜单权限表", tags = {"数据更新"})
    @SysLog("修改菜单权限表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sysMenu_update')")
    public R<Boolean> updateById(@RequestBody SysMenuEntity sysMenuEntity) {
        return R.ok(this.sysMenuService.updateById(sysMenuEntity));
    }

    /**
     * 通过menuId 删除菜单权限表
     *
     * @param menuId 菜单ID
     * @return R
     */
    @PreAuthorize("@pms.hasPermission('sysMenu_delete')")
    @Operation(description = "通过menuId 删除菜单权限表", summary = "通过menuId 删除菜单权限表", tags = {"数据删除"})
    @SysLog("通过id删除菜单权限表")
    @DeleteMapping("/{menuId}")
    public R<Boolean> removeById(@PathVariable("menuId") Long menuId) {
        return R.ok(this.sysMenuService.removeById(menuId));
    }

}
