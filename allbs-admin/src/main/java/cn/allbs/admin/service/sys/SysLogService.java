package cn.allbs.admin.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.allbs.admin.entity.sys.SysLogEntity;
import cn.allbs.admin.dto.sys.SysLogDTO;
import cn.allbs.admin.vo.sys.SysLogVO;

import java.util.List;

/**
 * 日志表(sys_log)表服务接口
 *
 * @author chenqi
 * @since 2024-03-08 17:39:07
 */
public interface SysLogService extends IService<SysLogEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page      分页参数
     * @param sysLogDTO 查询参数
     * @return IPage<SysLogVO> sysLogDTO
     */
    IPage<SysLogVO> queryPage(Page<SysLogDTO> page, SysLogDTO sysLogDTO);

    /**
     * 查询所有数据
     *
     * @param sysLogDTO 查询参数
     * @return List<SysLogVO>
     */
    List<SysLogVO> queryList(SysLogDTO sysLogDTO);
}
