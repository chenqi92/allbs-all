package cn.allbs.admin.security.service;

import cn.allbs.admin.entity.sys.SysUserEntity;
import cn.allbs.admin.security.constant.SecurityConstant;
import cn.allbs.admin.security.model.SysUser;
import cn.allbs.admin.security.model.UserInfo;
import cn.allbs.admin.service.sys.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 类 CustomUserServiceImpl 自定义用户登录校验
 *
 * @author ChenQi
 * @date 2024/3/8
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements UserDetailsService {

    private final SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = sysUserService.findUserInfoByUserName(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("指定用户不存在!");
        }
        // 权限标识的集合
        Set<String> dbAuthsSet = new HashSet<>(Arrays.asList(userInfo.getPermissions()));
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUserEntity user = userInfo.getSysUser();
        // 判断用户是否为正常使用的状态
        boolean enabled = SecurityConstant.STATUS_NORMAL.equals(user.getLockFlg());
        // @formatter:off
        return new SysUser(
                // 用户id
                user.getUserId(),
                // 用户手机号
                user.getNickname(),
                // 用户头像
                user.getAvatar(),
                // 用户名
                user.getUsername(),
                // 密码
                user.getPassword(),
                // 用户账号是否为正常使用的状态
                enabled,
                true,
                true,
                // 判断用户是否为锁定状态
                !SecurityConstant.STATUS_LOCK.equals(user.getLockFlg()),
                // 权限列表
                authorities,
                userInfo.getRoleName()
        );
        // @formatter:on
    }
}
