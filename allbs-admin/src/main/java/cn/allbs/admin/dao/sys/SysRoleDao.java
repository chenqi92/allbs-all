package cn.allbs.admin.dao.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.allbs.admin.entity.sys.SysRoleEntity;
import cn.allbs.admin.dto.sys.SysRoleDTO;
import cn.allbs.admin.vo.sys.SysRoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统角色表(sys_role)表数据库访问层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:08
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param sysRoleDTO 查询参数
     * @return IPage<SysRoleVO>
     */
    IPage<SysRoleVO> queryList(Page<SysRoleDTO> page, @Param("sysRoleDTO") SysRoleDTO sysRoleDTO);

    /**
     * 查询所有数据
     *
     * @param sysRoleDTO 查询参数
     * @return List<SysRoleVO>
     */
    List<SysRoleVO> queryList(@Param("sysRoleDTO") SysRoleDTO sysRoleDTO);
}
