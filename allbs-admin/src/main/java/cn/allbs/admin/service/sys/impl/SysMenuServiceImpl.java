package cn.allbs.admin.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.allbs.admin.dao.sys.SysMenuDao;
import cn.allbs.admin.entity.sys.SysMenuEntity;
import cn.allbs.admin.dto.sys.SysMenuDTO;
import cn.allbs.admin.vo.sys.SysMenuVO;
import cn.allbs.admin.service.sys.SysMenuService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 菜单权限表(sys_menu)表服务实现类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:07
 */
@Service("sysMenuService")
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

    private final SysMenuDao sysMenuDao;

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param sysMenuDTO 查询参数
     * @return 指定页码和条数的数据
     */
    @Override
    public IPage<SysMenuVO> queryPage(Page<SysMenuDTO> page, SysMenuDTO sysMenuDTO) {
        return this.sysMenuDao.queryList(page, sysMenuDTO);
    }

    /**
     * 查询所有数据
     *
     * @param sysMenuDTO 查询参数
     * @return 所有数据
     */
    @Override
    public List<SysMenuVO> queryList(SysMenuDTO sysMenuDTO) {
        return this.sysMenuDao.queryList(sysMenuDTO);
    }
}
