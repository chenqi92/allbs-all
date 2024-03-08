package cn.allbs.admin.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.allbs.admin.dao.sys.SysDictItemDao;
import cn.allbs.admin.entity.sys.SysDictItemEntity;
import cn.allbs.admin.dto.sys.SysDictItemDTO;
import cn.allbs.admin.vo.sys.SysDictItemVO;
import cn.allbs.admin.service.sys.SysDictItemService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 字典项(sys_dict_item)表服务实现类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:06
 */
@Service("sysDictItemService")
@RequiredArgsConstructor
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemDao, SysDictItemEntity> implements SysDictItemService {

    private final SysDictItemDao sysDictItemDao;

    /**
     * 分页查询所有数据
     *
     * @param page           分页参数
     * @param sysDictItemDTO 查询参数
     * @return 指定页码和条数的数据
     */
    @Override
    public IPage<SysDictItemVO> queryPage(Page<SysDictItemDTO> page, SysDictItemDTO sysDictItemDTO) {
        return this.sysDictItemDao.queryList(page, sysDictItemDTO);
    }

    /**
     * 查询所有数据
     *
     * @param sysDictItemDTO 查询参数
     * @return 所有数据
     */
    @Override
    public List<SysDictItemVO> queryList(SysDictItemDTO sysDictItemDTO) {
        return this.sysDictItemDao.queryList(sysDictItemDTO);
    }
}
