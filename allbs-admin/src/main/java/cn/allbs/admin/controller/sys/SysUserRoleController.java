package cn.allbs.admin.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.allbs.admin.config.core.R;
import cn.allbs.admin.config.annotation.SysLog;
import cn.allbs.admin.entity.sys.SysUserRoleEntity;
import cn.allbs.admin.dto.sys.SysUserRoleDTO;
import cn.allbs.admin.vo.sys.SysUserRoleVO;
import cn.allbs.admin.service.sys.SysUserRoleService;
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
 * 用户角色表(sys_user_role)表控制层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:10
 */
@Hidden
@RestController
@AllArgsConstructor
@RequestMapping("/sys/user/role")
@Tag(name = "sysUserRole", description = "用户角色表 管理")
public class SysUserRoleController {

    /**
     * 服务对象
     */
    private final SysUserRoleService sysUserRoleService;

    /**
     * 分页查询所有用户角色表信息
     *
     * @return List<SysUserRoleDTO> 分页当前账户下所有用户角色表信息
     */
    @Operation(description = "分页用户角色表信息表数据", summary = "分页查询用户角色表信息表数据", tags = {"分页查询所有数据"})
    @GetMapping("queryPage")
    @Parameters({@Parameter(description = "当前页", name = "current", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class)), @Parameter(description = "当前页条数", name = "size", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class))})
    public R<IPage<SysUserRoleVO>> queryPage(@ParameterObject Page<SysUserRoleDTO> page, @ParameterObject SysUserRoleDTO sysUserRoleDTO) {
        return R.ok(this.sysUserRoleService.queryPage(page, sysUserRoleDTO));
    }

    /**
     * 查询所有用户角色表信息
     *
     * @return List<SysUserRoleEntity> 当前账户下所有用户角色表信息
     */
    @Operation(description = "查询用户角色表信息表数据", summary = "查询用户角色表信息表数据", tags = {"查询所有数据"})
    @GetMapping("queryList")
    public R<List<SysUserRoleVO>> queryList(@ParameterObject SysUserRoleDTO sysUserRoleDTO) {
        return R.ok(this.sysUserRoleService.queryList(sysUserRoleDTO));
    }

    /**
     * 通过id查询 用户角色表
     *
     * @param userId 用户ID
     * @return R
     */
    @Operation(description = "通过userId查询", summary = "通过userId查询", tags = {"主键查询"})
    @GetMapping("/{userId}")
    @PreAuthorize("@pms.hasPermission('sysUserRole_list')")
    public R<SysUserRoleEntity> getById(@PathVariable("userId") Integer userId) {
        return R.ok(this.sysUserRoleService.getById(userId));
    }

    /**
     * 新增 用户角色表
     *
     * @param sysUserRoleEntity 用户角色表
     * @return R
     */
    @Operation(description = "新增用户角色表", summary = "新增用户角色表", tags = {"数据新增"})
    @SysLog("新增用户角色表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sysUserRole_save')")
    public R<Boolean> save(@RequestBody SysUserRoleEntity sysUserRoleEntity) {
        return R.ok(this.sysUserRoleService.save(sysUserRoleEntity));
    }

    /**
     * 修改用户角色表
     *
     * @param sysUserRoleEntity 用户角色表
     * @return R
     */
    @Operation(description = "修改用户角色表", summary = "修改用户角色表", tags = {"数据更新"})
    @SysLog("修改用户角色表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sysUserRole_update')")
    public R<Boolean> updateById(@RequestBody SysUserRoleEntity sysUserRoleEntity) {
        return R.ok(this.sysUserRoleService.updateById(sysUserRoleEntity));
    }

    /**
     * 通过userId 删除用户角色表
     *
     * @param userId 用户ID
     * @return R
     */
    @PreAuthorize("@pms.hasPermission('sysUserRole_delete')")
    @Operation(description = "通过userId 删除用户角色表", summary = "通过userId 删除用户角色表", tags = {"数据删除"})
    @SysLog("通过id删除用户角色表")
    @DeleteMapping("/{userId}")
    public R<Boolean> removeById(@PathVariable("userId") Integer userId) {
        return R.ok(this.sysUserRoleService.removeById(userId));
    }

}
