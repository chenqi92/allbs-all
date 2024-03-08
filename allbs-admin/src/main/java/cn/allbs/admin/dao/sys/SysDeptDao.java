package cn.allbs.admin.dao.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.allbs.admin.entity.sys.SysDeptEntity;
import cn.allbs.admin.dto.sys.SysDeptDTO;
import cn.allbs.admin.vo.sys.SysDeptVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门管理(sys_dept)表数据库访问层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:04
 */
@Mapper
public interface SysDeptDao extends BaseMapper<SysDeptEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param sysDeptDTO 查询参数
     * @return IPage<SysDeptVO>
     */
    IPage<SysDeptVO> queryList(Page<SysDeptDTO> page, @Param("sysDeptDTO") SysDeptDTO sysDeptDTO);

    /**
     * 查询所有数据
     *
     * @param sysDeptDTO 查询参数
     * @return List<SysDeptVO>
     */
    List<SysDeptVO> queryList(@Param("sysDeptDTO") SysDeptDTO sysDeptDTO);
}
