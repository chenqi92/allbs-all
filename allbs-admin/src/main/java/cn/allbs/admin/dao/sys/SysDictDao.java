package cn.allbs.admin.dao.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.allbs.admin.entity.sys.SysDictEntity;
import cn.allbs.admin.dto.sys.SysDictDTO;
import cn.allbs.admin.vo.sys.SysDictVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典表(sys_dict)表数据库访问层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:05
 */
@Mapper
public interface SysDictDao extends BaseMapper<SysDictEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param sysDictDTO 查询参数
     * @return IPage<SysDictVO>
     */
    IPage<SysDictVO> queryList(Page<SysDictDTO> page, @Param("sysDictDTO") SysDictDTO sysDictDTO);

    /**
     * 查询所有数据
     *
     * @param sysDictDTO 查询参数
     * @return List<SysDictVO>
     */
    List<SysDictVO> queryList(@Param("sysDictDTO") SysDictDTO sysDictDTO);
}
