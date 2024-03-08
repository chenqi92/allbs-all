package cn.allbs.admin.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.allbs.admin.config.core.R;
import cn.allbs.admin.config.annotation.SysLog;
import cn.allbs.admin.entity.sys.SysUserEntity;
import cn.allbs.admin.dto.sys.SysUserDTO;
import cn.allbs.admin.vo.sys.SysUserVO;
import cn.allbs.admin.service.sys.SysUserService;
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
 * 用户表(sys_user)表控制层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:09
 */
@Hidden
@RestController
@AllArgsConstructor
@RequestMapping("/sys/user")
@Tag(name = "sysUser", description = "用户表 管理")
public class SysUserController {

    /**
     * 服务对象
     */
    private final SysUserService sysUserService;

    /**
     * 分页查询所有用户表信息
     *
     * @return List<SysUserDTO> 分页当前账户下所有用户表信息
     */
    @Operation(description = "分页用户表信息表数据", summary = "分页查询用户表信息表数据", tags = {"分页查询所有数据"})
    @GetMapping("queryPage")
    @Parameters({@Parameter(description = "当前页", name = "current", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class)), @Parameter(description = "当前页条数", name = "size", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class))})
    public R<IPage<SysUserVO>> queryPage(@ParameterObject Page<SysUserDTO> page, @ParameterObject SysUserDTO sysUserDTO) {
        return R.ok(this.sysUserService.queryPage(page, sysUserDTO));
    }

    /**
     * 查询所有用户表信息
     *
     * @return List<SysUserEntity> 当前账户下所有用户表信息
     */
    @Operation(description = "查询用户表信息表数据", summary = "查询用户表信息表数据", tags = {"查询所有数据"})
    @GetMapping("queryList")
    public R<List<SysUserVO>> queryList(@ParameterObject SysUserDTO sysUserDTO) {
        return R.ok(this.sysUserService.queryList(sysUserDTO));
    }

    /**
     * 通过id查询 用户表
     *
     * @param userId 主键ID
     * @return R
     */
    @Operation(description = "通过userId查询", summary = "通过userId查询", tags = {"主键查询"})
    @GetMapping("/{userId}")
    @PreAuthorize("@pms.hasPermission('sysUser_list')")
    public R<SysUserEntity> getById(@PathVariable("userId") Long userId) {
        return R.ok(this.sysUserService.getById(userId));
    }

    /**
     * 新增 用户表
     *
     * @param sysUserEntity 用户表
     * @return R
     */
    @Operation(description = "新增用户表", summary = "新增用户表", tags = {"数据新增"})
    @SysLog("新增用户表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sysUser_save')")
    public R<Boolean> save(@RequestBody SysUserEntity sysUserEntity) {
        return R.ok(this.sysUserService.save(sysUserEntity));
    }

    /**
     * 修改用户表
     *
     * @param sysUserEntity 用户表
     * @return R
     */
    @Operation(description = "修改用户表", summary = "修改用户表", tags = {"数据更新"})
    @SysLog("修改用户表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sysUser_update')")
    public R<Boolean> updateById(@RequestBody SysUserEntity sysUserEntity) {
        return R.ok(this.sysUserService.updateById(sysUserEntity));
    }

    /**
     * 通过userId 删除用户表
     *
     * @param userId 主键ID
     * @return R
     */
    @PreAuthorize("@pms.hasPermission('sysUser_delete')")
    @Operation(description = "通过userId 删除用户表", summary = "通过userId 删除用户表", tags = {"数据删除"})
    @SysLog("通过id删除用户表")
    @DeleteMapping("/{userId}")
    public R<Boolean> removeById(@PathVariable("userId") Long userId) {
        return R.ok(this.sysUserService.removeById(userId));
    }

}
