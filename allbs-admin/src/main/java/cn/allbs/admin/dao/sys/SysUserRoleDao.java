package cn.allbs.admin.dao.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.allbs.admin.entity.sys.SysUserRoleEntity;
import cn.allbs.admin.dto.sys.SysUserRoleDTO;
import cn.allbs.admin.vo.sys.SysUserRoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色表(sys_user_role)表数据库访问层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:10
 */
@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRoleEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page           分页参数
     * @param sysUserRoleDTO 查询参数
     * @return IPage<SysUserRoleVO>
     */
    IPage<SysUserRoleVO> queryList(Page<SysUserRoleDTO> page, @Param("sysUserRoleDTO") SysUserRoleDTO sysUserRoleDTO);

    /**
     * 查询所有数据
     *
     * @param sysUserRoleDTO 查询参数
     * @return List<SysUserRoleVO>
     */
    List<SysUserRoleVO> queryList(@Param("sysUserRoleDTO") SysUserRoleDTO sysUserRoleDTO);
}
