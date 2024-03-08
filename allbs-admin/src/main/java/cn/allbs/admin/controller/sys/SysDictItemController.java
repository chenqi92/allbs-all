package cn.allbs.admin.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.allbs.admin.config.core.R;
import cn.allbs.admin.config.annotation.SysLog;
import cn.allbs.admin.entity.sys.SysDictItemEntity;
import cn.allbs.admin.dto.sys.SysDictItemDTO;
import cn.allbs.admin.vo.sys.SysDictItemVO;
import cn.allbs.admin.service.sys.SysDictItemService;
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
 * 字典项(sys_dict_item)表控制层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:05
 */
@Hidden
@RestController
@AllArgsConstructor
@RequestMapping("/sys/dict/item")
@Tag(name = "sysDictItem", description = "字典项 管理")
public class SysDictItemController {

    /**
     * 服务对象
     */
    private final SysDictItemService sysDictItemService;

    /**
     * 分页查询所有字典项信息
     *
     * @return List<SysDictItemDTO> 分页当前账户下所有字典项信息
     */
    @Operation(description = "分页字典项信息表数据", summary = "分页查询字典项信息表数据", tags = {"分页查询所有数据"})
    @GetMapping("queryPage")
    @Parameters({@Parameter(description = "当前页", name = "current", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class)), @Parameter(description = "当前页条数", name = "size", in = ParameterIn.QUERY, required = true, schema = @Schema(implementation = Integer.class))})
    public R<IPage<SysDictItemVO>> queryPage(@ParameterObject Page<SysDictItemDTO> page, @ParameterObject SysDictItemDTO sysDictItemDTO) {
        return R.ok(this.sysDictItemService.queryPage(page, sysDictItemDTO));
    }

    /**
     * 查询所有字典项信息
     *
     * @return List<SysDictItemEntity> 当前账户下所有字典项信息
     */
    @Operation(description = "查询字典项信息表数据", summary = "查询字典项信息表数据", tags = {"查询所有数据"})
    @GetMapping("queryList")
    public R<List<SysDictItemVO>> queryList(@ParameterObject SysDictItemDTO sysDictItemDTO) {
        return R.ok(this.sysDictItemService.queryList(sysDictItemDTO));
    }

    /**
     * 通过id查询 字典项
     *
     * @param id 编号
     * @return R
     */
    @Operation(description = "通过id查询", summary = "通过id查询", tags = {"主键查询"})
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sysDictItem_list')")
    public R<SysDictItemEntity> getById(@PathVariable("id") Long id) {
        return R.ok(this.sysDictItemService.getById(id));
    }

    /**
     * 新增 字典项
     *
     * @param sysDictItemEntity 字典项
     * @return R
     */
    @Operation(description = "新增字典项", summary = "新增字典项", tags = {"数据新增"})
    @SysLog("新增字典项")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sysDictItem_save')")
    public R<Boolean> save(@RequestBody SysDictItemEntity sysDictItemEntity) {
        return R.ok(this.sysDictItemService.save(sysDictItemEntity));
    }

    /**
     * 修改字典项
     *
     * @param sysDictItemEntity 字典项
     * @return R
     */
    @Operation(description = "修改字典项", summary = "修改字典项", tags = {"数据更新"})
    @SysLog("修改字典项")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sysDictItem_update')")
    public R<Boolean> updateById(@RequestBody SysDictItemEntity sysDictItemEntity) {
        return R.ok(this.sysDictItemService.updateById(sysDictItemEntity));
    }

    /**
     * 通过id 删除字典项
     *
     * @param id 编号
     * @return R
     */
    @PreAuthorize("@pms.hasPermission('sysDictItem_delete')")
    @Operation(description = "通过id 删除字典项", summary = "通过id 删除字典项", tags = {"数据删除"})
    @SysLog("通过id删除字典项")
    @DeleteMapping("/{id}")
    public R<Boolean> removeById(@PathVariable("id") Long id) {
        return R.ok(this.sysDictItemService.removeById(id));
    }

}
