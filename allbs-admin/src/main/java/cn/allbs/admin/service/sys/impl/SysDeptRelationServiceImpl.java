package cn.allbs.admin.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.allbs.admin.dao.sys.SysDeptRelationDao;
import cn.allbs.admin.entity.sys.SysDeptRelationEntity;
import cn.allbs.admin.dto.sys.SysDeptRelationDTO;
import cn.allbs.admin.vo.sys.SysDeptRelationVO;
import cn.allbs.admin.service.sys.SysDeptRelationService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 部门关系表(sys_dept_relation)表服务实现类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:05
 */
@Service("sysDeptRelationService")
@RequiredArgsConstructor
public class SysDeptRelationServiceImpl extends ServiceImpl<SysDeptRelationDao, SysDeptRelationEntity> implements SysDeptRelationService {

    private final SysDeptRelationDao sysDeptRelationDao;

    /**
     * 分页查询所有数据
     *
     * @param page               分页参数
     * @param sysDeptRelationDTO 查询参数
     * @return 指定页码和条数的数据
     */
    @Override
    public IPage<SysDeptRelationVO> queryPage(Page<SysDeptRelationDTO> page, SysDeptRelationDTO sysDeptRelationDTO) {
        return this.sysDeptRelationDao.queryList(page, sysDeptRelationDTO);
    }

    /**
     * 查询所有数据
     *
     * @param sysDeptRelationDTO 查询参数
     * @return 所有数据
     */
    @Override
    public List<SysDeptRelationVO> queryList(SysDeptRelationDTO sysDeptRelationDTO) {
        return this.sysDeptRelationDao.queryList(sysDeptRelationDTO);
    }
}
