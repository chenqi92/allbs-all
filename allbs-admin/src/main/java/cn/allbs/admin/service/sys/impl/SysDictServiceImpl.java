package cn.allbs.admin.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.allbs.admin.dao.sys.SysDictDao;
import cn.allbs.admin.entity.sys.SysDictEntity;
import cn.allbs.admin.dto.sys.SysDictDTO;
import cn.allbs.admin.vo.sys.SysDictVO;
import cn.allbs.admin.service.sys.SysDictService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 字典表(sys_dict)表服务实现类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:05
 */
@Service("sysDictService")
@RequiredArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDictEntity> implements SysDictService {

    private final SysDictDao sysDictDao;

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param sysDictDTO 查询参数
     * @return 指定页码和条数的数据
     */
    @Override
    public IPage<SysDictVO> queryPage(Page<SysDictDTO> page, SysDictDTO sysDictDTO) {
        return this.sysDictDao.queryList(page, sysDictDTO);
    }

    /**
     * 查询所有数据
     *
     * @param sysDictDTO 查询参数
     * @return 所有数据
     */
    @Override
    public List<SysDictVO> queryList(SysDictDTO sysDictDTO) {
        return this.sysDictDao.queryList(sysDictDTO);
    }
}
