package cn.allbs.admin.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.allbs.admin.dao.sys.SysLogDao;
import cn.allbs.admin.entity.sys.SysLogEntity;
import cn.allbs.admin.dto.sys.SysLogDTO;
import cn.allbs.admin.vo.sys.SysLogVO;
import cn.allbs.admin.service.sys.SysLogService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 日志表(sys_log)表服务实现类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:07
 */
@Service("sysLogService")
@RequiredArgsConstructor
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

    private final SysLogDao sysLogDao;

    /**
     * 分页查询所有数据
     *
     * @param page      分页参数
     * @param sysLogDTO 查询参数
     * @return 指定页码和条数的数据
     */
    @Override
    public IPage<SysLogVO> queryPage(Page<SysLogDTO> page, SysLogDTO sysLogDTO) {
        return this.sysLogDao.queryList(page, sysLogDTO);
    }

    /**
     * 查询所有数据
     *
     * @param sysLogDTO 查询参数
     * @return 所有数据
     */
    @Override
    public List<SysLogVO> queryList(SysLogDTO sysLogDTO) {
        return this.sysLogDao.queryList(sysLogDTO);
    }
}
