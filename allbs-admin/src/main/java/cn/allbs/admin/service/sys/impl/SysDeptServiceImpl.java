package cn.allbs.admin.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.allbs.admin.dao.sys.SysDeptDao;
import cn.allbs.admin.entity.sys.SysDeptEntity;
import cn.allbs.admin.dto.sys.SysDeptDTO;
import cn.allbs.admin.vo.sys.SysDeptVO;
import cn.allbs.admin.service.sys.SysDeptService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 部门管理(sys_dept)表服务实现类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:04
 */
@Service("sysDeptService")
@RequiredArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {

    private final SysDeptDao sysDeptDao;

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param sysDeptDTO 查询参数
     * @return 指定页码和条数的数据
     */
    @Override
    public IPage<SysDeptVO> queryPage(Page<SysDeptDTO> page, SysDeptDTO sysDeptDTO) {
        return this.sysDeptDao.queryList(page, sysDeptDTO);
    }

    /**
     * 查询所有数据
     *
     * @param sysDeptDTO 查询参数
     * @return 所有数据
     */
    @Override
    public List<SysDeptVO> queryList(SysDeptDTO sysDeptDTO) {
        return this.sysDeptDao.queryList(sysDeptDTO);
    }
}
