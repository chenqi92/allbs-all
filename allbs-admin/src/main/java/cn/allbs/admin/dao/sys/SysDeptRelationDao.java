package cn.allbs.admin.dao.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.allbs.admin.entity.sys.SysDeptRelationEntity;
import cn.allbs.admin.dto.sys.SysDeptRelationDTO;
import cn.allbs.admin.vo.sys.SysDeptRelationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门关系表(sys_dept_relation)表数据库访问层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:05
 */
@Mapper
public interface SysDeptRelationDao extends BaseMapper<SysDeptRelationEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page               分页参数
     * @param sysDeptRelationDTO 查询参数
     * @return IPage<SysDeptRelationVO>
     */
    IPage<SysDeptRelationVO> queryList(Page<SysDeptRelationDTO> page, @Param("sysDeptRelationDTO") SysDeptRelationDTO sysDeptRelationDTO);

    /**
     * 查询所有数据
     *
     * @param sysDeptRelationDTO 查询参数
     * @return List<SysDeptRelationVO>
     */
    List<SysDeptRelationVO> queryList(@Param("sysDeptRelationDTO") SysDeptRelationDTO sysDeptRelationDTO);
}
