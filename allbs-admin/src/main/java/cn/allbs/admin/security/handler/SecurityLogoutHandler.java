package cn.allbs.admin.security.handler;

import cn.allbs.admin.security.constant.SecurityConstant;
import cn.allbs.admin.security.enums.SystemCode;
import cn.allbs.admin.security.utils.ResponseUtil;
import cn.allbs.admin.security.utils.TokenUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.StringUtils;

import java.io.IOException;

import static cn.allbs.admin.config.constant.CacheConstant.CACHE_TOKEN;
import static cn.allbs.admin.security.constant.SecurityConstant.BEARER_TYPE;

/**
 * 类 SecurityLogoutHandler
 *
 * @author ChenQi
 * @date 2024/3/15
 */
public class SecurityLogoutHandler implements LogoutHandler {

    private final RedisTemplate<Object, Object> redisTemplate;

    public SecurityLogoutHandler(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 从header 获取token
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        try {
            // 判断头部中是否含有以Bearer开头的token内容
            if (!StringUtils.hasText(token) || !token.startsWith(BEARER_TYPE)) {
                ResponseUtil.write(response, SystemCode.FORBIDDEN_401);
                return;
            }
            // 解析登出用户名
            String username = TokenUtil.getUsernameFromToken(token.replace(SecurityConstant.BEARER_TYPE, StringPool.EMPTY));
            if (!StringUtils.hasText(username)) {
                ResponseUtil.write(response, SystemCode.TOKEN_NOT_IN_SYSTEM);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        redisTemplate.delete(CACHE_TOKEN + token);
    }
}
