package cn.allbs.admin.dao.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.allbs.admin.entity.sys.SysLogEntity;
import cn.allbs.admin.dto.sys.SysLogDTO;
import cn.allbs.admin.vo.sys.SysLogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 日志表(sys_log)表数据库访问层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:07
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page      分页参数
     * @param sysLogDTO 查询参数
     * @return IPage<SysLogVO>
     */
    IPage<SysLogVO> queryList(Page<SysLogDTO> page, @Param("sysLogDTO") SysLogDTO sysLogDTO);

    /**
     * 查询所有数据
     *
     * @param sysLogDTO 查询参数
     * @return List<SysLogVO>
     */
    List<SysLogVO> queryList(@Param("sysLogDTO") SysLogDTO sysLogDTO);
}
