package cn.allbs.admin.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.allbs.admin.config.core.R;
import cn.allbs.admin.config.annotation.SysLog;
import cn.allbs.admin.entity.sys.SysFileEntity;
import cn.allbs.admin.dto.sys.SysFileDTO;
import cn.allbs.admin.vo.sys.SysFileVO;
import cn.allbs.admin.service.sys.SysFileService;
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
 * 文件管理表(sys_file)表控制层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:06
 */
@Hidden
@RestController
@AllArgsConstructor
@RequestMapping("/sys/file")
@Tag(name = "sysFile", description = "文件管理表 管理")
public class SysFileController {

    /**
     * 服务对象
     */
    private final SysFileService sysFileService;

    /**
     * 分页查询所有文件管理表信息
     *
     * @return List<SysFileDTO> 分页当前账户下所有文件管理表信息
     */
    @Operation(description = "分页文件管理表信息表数据", summary = "分页查询文件管理表信息表数据", tags = {"分页查询所有数据"})
    @GetMapping("queryPage")
    @Parameters({@Parameter(description = "当前页", name = "current", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class)), @Parameter(description = "当前页条数", name = "size", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class))})
    public R<IPage<SysFileVO>> queryPage(@ParameterObject Page<SysFileDTO> page, @ParameterObject SysFileDTO sysFileDTO) {
        return R.ok(this.sysFileService.queryPage(page, sysFileDTO));
    }

    /**
     * 查询所有文件管理表信息
     *
     * @return List<SysFileEntity> 当前账户下所有文件管理表信息
     */
    @Operation(description = "查询文件管理表信息表数据", summary = "查询文件管理表信息表数据", tags = {"查询所有数据"})
    @GetMapping("queryList")
    public R<List<SysFileVO>> queryList(@ParameterObject SysFileDTO sysFileDTO) {
        return R.ok(this.sysFileService.queryList(sysFileDTO));
    }

    /**
     * 通过id查询 文件管理表
     *
     * @param id 编号
     * @return R
     */
    @Operation(description = "通过id查询", summary = "通过id查询", tags = {"主键查询"})
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sysFile_list')")
    public R<SysFileEntity> getById(@PathVariable("id") Long id) {
        return R.ok(this.sysFileService.getById(id));
    }

    /**
     * 新增 文件管理表
     *
     * @param sysFileEntity 文件管理表
     * @return R
     */
    @Operation(description = "新增文件管理表", summary = "新增文件管理表", tags = {"数据新增"})
    @SysLog("新增文件管理表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sysFile_save')")
    public R<Boolean> save(@RequestBody SysFileEntity sysFileEntity) {
        return R.ok(this.sysFileService.save(sysFileEntity));
    }

    /**
     * 修改文件管理表
     *
     * @param sysFileEntity 文件管理表
     * @return R
     */
    @Operation(description = "修改文件管理表", summary = "修改文件管理表", tags = {"数据更新"})
    @SysLog("修改文件管理表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sysFile_update')")
    public R<Boolean> updateById(@RequestBody SysFileEntity sysFileEntity) {
        return R.ok(this.sysFileService.updateById(sysFileEntity));
    }

    /**
     * 通过id 删除文件管理表
     *
     * @param id 编号
     * @return R
     */
    @PreAuthorize("@pms.hasPermission('sysFile_delete')")
    @Operation(description = "通过id 删除文件管理表", summary = "通过id 删除文件管理表", tags = {"数据删除"})
    @SysLog("通过id删除文件管理表")
    @DeleteMapping("/{id}")
    public R<Boolean> removeById(@PathVariable("id") Long id) {
        return R.ok(this.sysFileService.removeById(id));
    }

}
