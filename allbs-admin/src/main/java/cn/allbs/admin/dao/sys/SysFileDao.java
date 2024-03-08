package cn.allbs.admin.dao.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.allbs.admin.entity.sys.SysFileEntity;
import cn.allbs.admin.dto.sys.SysFileDTO;
import cn.allbs.admin.vo.sys.SysFileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文件管理表(sys_file)表数据库访问层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:06
 */
@Mapper
public interface SysFileDao extends BaseMapper<SysFileEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param sysFileDTO 查询参数
     * @return IPage<SysFileVO>
     */
    IPage<SysFileVO> queryList(Page<SysFileDTO> page, @Param("sysFileDTO") SysFileDTO sysFileDTO);

    /**
     * 查询所有数据
     *
     * @param sysFileDTO 查询参数
     * @return List<SysFileVO>
     */
    List<SysFileVO> queryList(@Param("sysFileDTO") SysFileDTO sysFileDTO);
}
