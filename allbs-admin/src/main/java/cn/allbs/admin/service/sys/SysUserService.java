package cn.allbs.admin.service.sys;

import cn.allbs.admin.security.model.UserInfo;
import cn.allbs.admin.dto.sys.SysUserDTO;
import cn.allbs.admin.entity.sys.SysUserEntity;
import cn.allbs.admin.vo.sys.SysUserVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户表(sys_user)表服务接口
 *
 * @author chenqi
 * @since 2024-03-08 17:39:09
 */
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param sysUserDTO 查询参数
     * @return IPage<SysUserVO> sysUserDTO
     */
    IPage<SysUserVO> queryPage(Page<SysUserDTO> page, SysUserDTO sysUserDTO);

    /**
     * 查询所有数据
     *
     * @param sysUserDTO 查询参数
     * @return List<SysUserVO>
     */
    List<SysUserVO> queryList(SysUserDTO sysUserDTO);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户
     * @return userInfo
     */
    UserInfo findUserInfoByUserName(String username);
}
