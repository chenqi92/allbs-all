package cn.allbs.admin.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.allbs.admin.dao.sys.SysFileDao;
import cn.allbs.admin.entity.sys.SysFileEntity;
import cn.allbs.admin.dto.sys.SysFileDTO;
import cn.allbs.admin.vo.sys.SysFileVO;
import cn.allbs.admin.service.sys.SysFileService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 文件管理表(sys_file)表服务实现类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:06
 */
@Service("sysFileService")
@RequiredArgsConstructor
public class SysFileServiceImpl extends ServiceImpl<SysFileDao, SysFileEntity> implements SysFileService {

    private final SysFileDao sysFileDao;

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param sysFileDTO 查询参数
     * @return 指定页码和条数的数据
     */
    @Override
    public IPage<SysFileVO> queryPage(Page<SysFileDTO> page, SysFileDTO sysFileDTO) {
        return this.sysFileDao.queryList(page, sysFileDTO);
    }

    /**
     * 查询所有数据
     *
     * @param sysFileDTO 查询参数
     * @return 所有数据
     */
    @Override
    public List<SysFileVO> queryList(SysFileDTO sysFileDTO) {
        return this.sysFileDao.queryList(sysFileDTO);
    }
}
