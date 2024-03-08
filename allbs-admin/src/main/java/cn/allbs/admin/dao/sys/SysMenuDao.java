package cn.allbs.admin.dao.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.allbs.admin.entity.sys.SysMenuEntity;
import cn.allbs.admin.dto.sys.SysMenuDTO;
import cn.allbs.admin.vo.sys.SysMenuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单权限表(sys_menu)表数据库访问层
 *
 * @author chenqi
 * @since 2024-03-08 17:39:07
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param sysMenuDTO 查询参数
     * @return IPage<SysMenuVO>
     */
    IPage<SysMenuVO> queryList(Page<SysMenuDTO> page, @Param("sysMenuDTO") SysMenuDTO sysMenuDTO);

    /**
     * 查询所有数据
     *
     * @param sysMenuDTO 查询参数
     * @return List<SysMenuVO>
     */
    List<SysMenuVO> queryList(@Param("sysMenuDTO") SysMenuDTO sysMenuDTO);
}
