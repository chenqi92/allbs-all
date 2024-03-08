package cn.allbs.admin.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.allbs.admin.entity.sys.SysRoleEntity;
import cn.allbs.admin.dto.sys.SysRoleDTO;
import cn.allbs.admin.vo.sys.SysRoleVO;

import java.util.List;

/**
 * 系统角色表(sys_role)表服务接口
 *
 * @author chenqi
 * @since 2024-03-08 17:39:08
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param sysRoleDTO 查询参数
     * @return IPage<SysRoleVO> sysRoleDTO
     */
    IPage<SysRoleVO> queryPage(Page<SysRoleDTO> page, SysRoleDTO sysRoleDTO);

    /**
     * 查询所有数据
     *
     * @param sysRoleDTO 查询参数
     * @return List<SysRoleVO>
     */
    List<SysRoleVO> queryList(SysRoleDTO sysRoleDTO);
}
