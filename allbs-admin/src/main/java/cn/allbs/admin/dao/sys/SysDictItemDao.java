package cn.allbs.admin.dao.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.allbs.admin.entity.sys.SysDictItemEntity;
import cn.allbs.admin.dto.sys.SysDictItemDTO;
import cn.allbs.admin.vo.sys.SysDictItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典项(sys_dict_item)表数据库访问层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:06
 */
@Mapper
public interface SysDictItemDao extends BaseMapper<SysDictItemEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page           分页参数
     * @param sysDictItemDTO 查询参数
     * @return IPage<SysDictItemVO>
     */
    IPage<SysDictItemVO> queryList(Page<SysDictItemDTO> page, @Param("sysDictItemDTO") SysDictItemDTO sysDictItemDTO);

    /**
     * 查询所有数据
     *
     * @param sysDictItemDTO 查询参数
     * @return List<SysDictItemVO>
     */
    List<SysDictItemVO> queryList(@Param("sysDictItemDTO") SysDictItemDTO sysDictItemDTO);
}
