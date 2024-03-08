package cn.allbs.admin.dao.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.allbs.admin.entity.sys.SysRoleMenuEntity;
import cn.allbs.admin.dto.sys.SysRoleMenuDTO;
import cn.allbs.admin.vo.sys.SysRoleMenuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色菜单表(sys_role_menu)表数据库访问层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:09
 */
@Mapper
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenuEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page           分页参数
     * @param sysRoleMenuDTO 查询参数
     * @return IPage<SysRoleMenuVO>
     */
    IPage<SysRoleMenuVO> queryList(Page<SysRoleMenuDTO> page, @Param("sysRoleMenuDTO") SysRoleMenuDTO sysRoleMenuDTO);

    /**
     * 查询所有数据
     *
     * @param sysRoleMenuDTO 查询参数
     * @return List<SysRoleMenuVO>
     */
    List<SysRoleMenuVO> queryList(@Param("sysRoleMenuDTO") SysRoleMenuDTO sysRoleMenuDTO);
}
