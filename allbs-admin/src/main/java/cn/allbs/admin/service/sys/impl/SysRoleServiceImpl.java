package cn.allbs.admin.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.allbs.admin.dao.sys.SysRoleDao;
import cn.allbs.admin.entity.sys.SysRoleEntity;
import cn.allbs.admin.dto.sys.SysRoleDTO;
import cn.allbs.admin.vo.sys.SysRoleVO;
import cn.allbs.admin.service.sys.SysRoleService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 系统角色表(sys_role)表服务实现类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:08
 */
@Service("sysRoleService")
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

    private final SysRoleDao sysRoleDao;

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param sysRoleDTO 查询参数
     * @return 指定页码和条数的数据
     */
    @Override
    public IPage<SysRoleVO> queryPage(Page<SysRoleDTO> page, SysRoleDTO sysRoleDTO) {
        return this.sysRoleDao.queryList(page, sysRoleDTO);
    }

    /**
     * 查询所有数据
     *
     * @param sysRoleDTO 查询参数
     * @return 所有数据
     */
    @Override
    public List<SysRoleVO> queryList(SysRoleDTO sysRoleDTO) {
        return this.sysRoleDao.queryList(sysRoleDTO);
    }
}
