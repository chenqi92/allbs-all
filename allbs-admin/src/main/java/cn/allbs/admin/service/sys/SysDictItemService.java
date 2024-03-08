package cn.allbs.admin.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.allbs.admin.entity.sys.SysDictItemEntity;
import cn.allbs.admin.dto.sys.SysDictItemDTO;
import cn.allbs.admin.vo.sys.SysDictItemVO;

import java.util.List;

/**
 * 字典项(sys_dict_item)表服务接口
 *
 * @author chenqi
 * @since 2024-03-08 17:39:06
 */
public interface SysDictItemService extends IService<SysDictItemEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page           分页参数
     * @param sysDictItemDTO 查询参数
     * @return IPage<SysDictItemVO> sysDictItemDTO
     */
    IPage<SysDictItemVO> queryPage(Page<SysDictItemDTO> page, SysDictItemDTO sysDictItemDTO);

    /**
     * 查询所有数据
     *
     * @param sysDictItemDTO 查询参数
     * @return List<SysDictItemVO>
     */
    List<SysDictItemVO> queryList(SysDictItemDTO sysDictItemDTO);
}
