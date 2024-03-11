package cn.allbs.admin.service.sys.impl;

import cn.allbs.admin.config.dto.UserInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.allbs.admin.dao.sys.SysUserDao;
import cn.allbs.admin.entity.sys.SysUserEntity;
import cn.allbs.admin.dto.sys.SysUserDTO;
import cn.allbs.admin.vo.sys.SysUserVO;
import cn.allbs.admin.service.sys.SysUserService;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 用户表(sys_user)表服务实现类
 *
 * @author chenqi
 * @since 2024-03-08 17:39:09
 */
@Service("sysUserService")
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    private final SysUserDao sysUserDao;

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param sysUserDTO 查询参数
     * @return 指定页码和条数的数据
     */
    @Override
    public IPage<SysUserVO> queryPage(Page<SysUserDTO> page, SysUserDTO sysUserDTO) {
        return this.sysUserDao.queryList(page, sysUserDTO);
    }

    /**
     * 查询所有数据
     *
     * @param sysUserDTO 查询参数
     * @return 所有数据
     */
    @Override
    public List<SysUserVO> queryList(SysUserDTO sysUserDTO) {
        return this.sysUserDao.queryList(sysUserDTO);
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户
     * @return userInfo
     */
    @Override
    public UserInfo findUserInfoByUserName(String username) {
//        Cache cache = cacheManager.getCache(USER_DETAILS);
//        if (cache != null && cache.get(username) != null) {
//            return cache.get(username, UserInfo.class);
//        }
//        SysUserEntity sysUserEntity = sysUserDao.selectOne(Wrappers.<SysUserEntity>query().lambda().eq(SysUserEntity::getUsername, username));
//        if (BeanUtil.isEmpty(sysUserEntity)) {
//            throw new AuthorizationException("不存在的用户名");
//        }
//        UserInfo userInfo = this.getUserInfo(sysUserEntity);
//        assert cache != null;
//        cache.put(username, userInfo);
        return userInfo;
    }
}
