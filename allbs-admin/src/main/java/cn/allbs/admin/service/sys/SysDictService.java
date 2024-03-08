package cn.allbs.admin.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.allbs.admin.entity.sys.SysDictEntity;
import cn.allbs.admin.dto.sys.SysDictDTO;
import cn.allbs.admin.vo.sys.SysDictVO;

import java.util.List;

/**
 * 字典表(sys_dict)表服务接口
 *
 * @author chenqi
 * @since 2024-03-08 17:39:05
 */
public interface SysDictService extends IService<SysDictEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param sysDictDTO 查询参数
     * @return IPage<SysDictVO> sysDictDTO
     */
    IPage<SysDictVO> queryPage(Page<SysDictDTO> page, SysDictDTO sysDictDTO);

    /**
     * 查询所有数据
     *
     * @param sysDictDTO 查询参数
     * @return List<SysDictVO>
     */
    List<SysDictVO> queryList(SysDictDTO sysDictDTO);
}
