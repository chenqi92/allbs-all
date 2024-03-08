package cn.allbs.admin.security.utils;

import cn.allbs.admin.security.model.SysUser;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * 类 SecurityUtil
 *
 * @author ChenQi
 * @date 2024/3/8
 */
@Slf4j
@UtilityClass
public class SecurityUtil {

    /**
     * 获取当前用户名
     *
     * @return 用户名
     */
    public static String getUsername() {
        String username = null;
        Authentication authentication = getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public static String getUsername(Authentication authentication) {
        String username = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof String) {
                username = (String) principal;
            }
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }

    /**
     * 获取当前用户信息
     *
     * @return SysUser
     */
    public static SysUser getUser() {
        Authentication authentication = getAuthentication();
        return getUser(authentication);
    }

    /**
     * 获取用户
     *
     * @param authentication Authentication
     * @return SysUser
     */
    public static SysUser getUser(Authentication authentication) {
        Object principal = Optional.ofNullable(authentication).map(Authentication::getPrincipal).orElse(null);
        if (principal instanceof SysUser) {
            return (SysUser) principal;
        }
        return null;
    }

    /**
     * 获取当前登录信息
     *
     * @return Authentication
     */
    public static Authentication getAuthentication() {
        if (SecurityContextHolder.getContext() == null) {
            return null;
        }
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
