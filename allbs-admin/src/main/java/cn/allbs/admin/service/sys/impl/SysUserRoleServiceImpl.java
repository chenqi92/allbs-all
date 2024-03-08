package cn.allbs.admin.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.allbs.admin.dao.sys.SysUserRoleDao;
import cn.allbs.admin.entity.sys.SysUserRoleEntity;
import cn.allbs.admin.dto.sys.SysUserRoleDTO;
import cn.allbs.admin.vo.sys.SysUserRoleVO;
import cn.allbs.admin.service.sys.SysUserRoleService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 用户角色表(sys_user_role)表服务实现类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:10
 */
@Service("sysUserRoleService")
@RequiredArgsConstructor
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {

    private final SysUserRoleDao sysUserRoleDao;

    /**
     * 分页查询所有数据
     *
     * @param page           分页参数
     * @param sysUserRoleDTO 查询参数
     * @return 指定页码和条数的数据
     */
    @Override
    public IPage<SysUserRoleVO> queryPage(Page<SysUserRoleDTO> page, SysUserRoleDTO sysUserRoleDTO) {
        return this.sysUserRoleDao.queryList(page, sysUserRoleDTO);
    }

    /**
     * 查询所有数据
     *
     * @param sysUserRoleDTO 查询参数
     * @return 所有数据
     */
    @Override
    public List<SysUserRoleVO> queryList(SysUserRoleDTO sysUserRoleDTO) {
        return this.sysUserRoleDao.queryList(sysUserRoleDTO);
    }
}
