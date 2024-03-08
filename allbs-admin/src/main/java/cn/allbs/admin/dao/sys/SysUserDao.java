package cn.allbs.admin.dao.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.allbs.admin.entity.sys.SysUserEntity;
import cn.allbs.admin.dto.sys.SysUserDTO;
import cn.allbs.admin.vo.sys.SysUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表(sys_user)表数据库访问层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:09
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param sysUserDTO 查询参数
     * @return IPage<SysUserVO>
     */
    IPage<SysUserVO> queryList(Page<SysUserDTO> page, @Param("sysUserDTO") SysUserDTO sysUserDTO);

    /**
     * 查询所有数据
     *
     * @param sysUserDTO 查询参数
     * @return List<SysUserVO>
     */
    List<SysUserVO> queryList(@Param("sysUserDTO") SysUserDTO sysUserDTO);
}
