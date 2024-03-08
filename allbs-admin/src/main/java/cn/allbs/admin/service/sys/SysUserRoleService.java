package cn.allbs.admin.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.allbs.admin.entity.sys.SysUserRoleEntity;
import cn.allbs.admin.dto.sys.SysUserRoleDTO;
import cn.allbs.admin.vo.sys.SysUserRoleVO;

import java.util.List;

/**
 * 用户角色表(sys_user_role)表服务接口
 *
 * @author chenqi
 * @since 2024-03-08 17:39:10
 */
public interface SysUserRoleService extends IService<SysUserRoleEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page           分页参数
     * @param sysUserRoleDTO 查询参数
     * @return IPage<SysUserRoleVO> sysUserRoleDTO
     */
    IPage<SysUserRoleVO> queryPage(Page<SysUserRoleDTO> page, SysUserRoleDTO sysUserRoleDTO);

    /**
     * 查询所有数据
     *
     * @param sysUserRoleDTO 查询参数
     * @return List<SysUserRoleVO>
     */
    List<SysUserRoleVO> queryList(SysUserRoleDTO sysUserRoleDTO);
}
