package cn.allbs.admin.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.allbs.admin.entity.sys.SysRoleMenuEntity;
import cn.allbs.admin.dto.sys.SysRoleMenuDTO;
import cn.allbs.admin.vo.sys.SysRoleMenuVO;

import java.util.List;

/**
 * 角色菜单表(sys_role_menu)表服务接口
 *
 * @author chenqi
 * @since 2024-03-08 17:39:09
 */
public interface SysRoleMenuService extends IService<SysRoleMenuEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page           分页参数
     * @param sysRoleMenuDTO 查询参数
     * @return IPage<SysRoleMenuVO> sysRoleMenuDTO
     */
    IPage<SysRoleMenuVO> queryPage(Page<SysRoleMenuDTO> page, SysRoleMenuDTO sysRoleMenuDTO);

    /**
     * 查询所有数据
     *
     * @param sysRoleMenuDTO 查询参数
     * @return List<SysRoleMenuVO>
     */
    List<SysRoleMenuVO> queryList(SysRoleMenuDTO sysRoleMenuDTO);
}
