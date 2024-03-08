package cn.allbs.admin.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.allbs.admin.entity.sys.SysDeptRelationEntity;
import cn.allbs.admin.dto.sys.SysDeptRelationDTO;
import cn.allbs.admin.vo.sys.SysDeptRelationVO;

import java.util.List;

/**
 * 部门关系表(sys_dept_relation)表服务接口
 *
 * @author chenqi
 * @since 2024-03-08 17:39:05
 */
public interface SysDeptRelationService extends IService<SysDeptRelationEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page               分页参数
     * @param sysDeptRelationDTO 查询参数
     * @return IPage<SysDeptRelationVO> sysDeptRelationDTO
     */
    IPage<SysDeptRelationVO> queryPage(Page<SysDeptRelationDTO> page, SysDeptRelationDTO sysDeptRelationDTO);

    /**
     * 查询所有数据
     *
     * @param sysDeptRelationDTO 查询参数
     * @return List<SysDeptRelationVO>
     */
    List<SysDeptRelationVO> queryList(SysDeptRelationDTO sysDeptRelationDTO);
}
