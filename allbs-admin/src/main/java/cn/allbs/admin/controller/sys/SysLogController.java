package cn.allbs.admin.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.allbs.admin.config.core.R;
import cn.allbs.admin.config.annotation.SysLog;
import cn.allbs.admin.entity.sys.SysLogEntity;
import cn.allbs.admin.dto.sys.SysLogDTO;
import cn.allbs.admin.vo.sys.SysLogVO;
import cn.allbs.admin.service.sys.SysLogService;
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
 * 日志表(sys_log)表控制层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:07
 */
@Hidden
@RestController
@AllArgsConstructor
@RequestMapping("/sys/log")
@Tag(name = "sysLog", description = "日志表 管理")
public class SysLogController {

    /**
     * 服务对象
     */
    private final SysLogService sysLogService;

    /**
     * 分页查询所有日志表信息
     *
     * @return List<SysLogDTO> 分页当前账户下所有日志表信息
     */
    @Operation(description = "分页日志表信息表数据", summary = "分页查询日志表信息表数据", tags = {"分页查询所有数据"})
    @GetMapping("queryPage")
    @Parameters({@Parameter(description = "当前页", name = "current", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class)), @Parameter(description = "当前页条数", name = "size", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class))})
    public R<IPage<SysLogVO>> queryPage(@ParameterObject Page<SysLogDTO> page, @ParameterObject SysLogDTO sysLogDTO) {
        return R.ok(this.sysLogService.queryPage(page, sysLogDTO));
    }

    /**
     * 查询所有日志表信息
     *
     * @return List<SysLogEntity> 当前账户下所有日志表信息
     */
    @Operation(description = "查询日志表信息表数据", summary = "查询日志表信息表数据", tags = {"查询所有数据"})
    @GetMapping("queryList")
    public R<List<SysLogVO>> queryList(@ParameterObject SysLogDTO sysLogDTO) {
        return R.ok(this.sysLogService.queryList(sysLogDTO));
    }

    /**
     * 通过id查询 日志表
     *
     * @param id 日志主键
     * @return R
     */
    @Operation(description = "通过id查询", summary = "通过id查询", tags = {"主键查询"})
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sysLog_list')")
    public R<SysLogEntity> getById(@PathVariable("id") Long id) {
        return R.ok(this.sysLogService.getById(id));
    }

    /**
     * 新增 日志表
     *
     * @param sysLogEntity 日志表
     * @return R
     */
    @Operation(description = "新增日志表", summary = "新增日志表", tags = {"数据新增"})
    @SysLog("新增日志表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sysLog_save')")
    public R<Boolean> save(@RequestBody SysLogEntity sysLogEntity) {
        return R.ok(this.sysLogService.save(sysLogEntity));
    }

    /**
     * 修改日志表
     *
     * @param sysLogEntity 日志表
     * @return R
     */
    @Operation(description = "修改日志表", summary = "修改日志表", tags = {"数据更新"})
    @SysLog("修改日志表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sysLog_update')")
    public R<Boolean> updateById(@RequestBody SysLogEntity sysLogEntity) {
        return R.ok(this.sysLogService.updateById(sysLogEntity));
    }

    /**
     * 通过id 删除日志表
     *
     * @param id 日志主键
     * @return R
     */
    @PreAuthorize("@pms.hasPermission('sysLog_delete')")
    @Operation(description = "通过id 删除日志表", summary = "通过id 删除日志表", tags = {"数据删除"})
    @SysLog("通过id删除日志表")
    @DeleteMapping("/{id}")
    public R<Boolean> removeById(@PathVariable("id") Long id) {
        return R.ok(this.sysLogService.removeById(id));
    }

}
