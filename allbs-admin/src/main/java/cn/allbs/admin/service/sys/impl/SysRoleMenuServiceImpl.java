package cn.allbs.admin.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.allbs.admin.dao.sys.SysRoleMenuDao;
import cn.allbs.admin.entity.sys.SysRoleMenuEntity;
import cn.allbs.admin.dto.sys.SysRoleMenuDTO;
import cn.allbs.admin.vo.sys.SysRoleMenuVO;
import cn.allbs.admin.service.sys.SysRoleMenuService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 角色菜单表(sys_role_menu)表服务实现类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:09
 */
@Service("sysRoleMenuService")
@RequiredArgsConstructor
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

    private final SysRoleMenuDao sysRoleMenuDao;

    /**
     * 分页查询所有数据
     *
     * @param page           分页参数
     * @param sysRoleMenuDTO 查询参数
     * @return 指定页码和条数的数据
     */
    @Override
    public IPage<SysRoleMenuVO> queryPage(Page<SysRoleMenuDTO> page, SysRoleMenuDTO sysRoleMenuDTO) {
        return this.sysRoleMenuDao.queryList(page, sysRoleMenuDTO);
    }

    /**
     * 查询所有数据
     *
     * @param sysRoleMenuDTO 查询参数
     * @return 所有数据
     */
    @Override
    public List<SysRoleMenuVO> queryList(SysRoleMenuDTO sysRoleMenuDTO) {
        return this.sysRoleMenuDao.queryList(sysRoleMenuDTO);
    }
}
