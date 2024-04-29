package cn.allbs.admin.security.service;

import cn.allbs.admin.config.constant.StringPool;
import cn.allbs.admin.config.core.R;
import cn.allbs.admin.security.grant.CustomJwtToken;
import cn.allbs.admin.security.model.LoginRequest;
import cn.allbs.admin.security.model.SysUser;
import cn.allbs.admin.security.model.UserNameLoginRequest;
import cn.allbs.admin.security.utils.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static cn.allbs.admin.config.constant.CacheConstant.CACHE_TOKEN;
import static cn.allbs.admin.config.constant.CacheConstant.REFRESH_TOKEN;

/**
 * 类 UsernamePasswordLoginService
 *
 * @author ChenQi
 * @date 2024/4/28
 */
@Service
@RequiredArgsConstructor
public class UsernamePasswordLoginService implements LoginService {
    private final AuthenticationManager authenticationManager;
    private final RedisTemplate<Object, Object> redisTemplate;
    private final TokenUtil tokenUtil;

    /**
     * 登录
     *
     * @param request 登录请求
     * @return 登录结果
     */
    @Override
    public R<?> authenticate(LoginRequest request) {
        UserNameLoginRequest userNameRequest = (UserNameLoginRequest) request;
        // 账号密码校验
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userNameRequest.getUsername(), userNameRequest.getPassword())
        );
        String accessTokenKey = CACHE_TOKEN + userNameRequest.getUsername();
        String refreshTokenKey = REFRESH_TOKEN + userNameRequest.getUsername();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SysUser sysUser = (SysUser) authentication.getPrincipal();
        // 生成并返回token给客户端，后续访问携带此token
        CustomJwtToken token = new CustomJwtToken(UUID.randomUUID().toString(), sysUser);
        token.setUsername(userNameRequest.getUsername());
        // 查看是否会踢同用户下线 如果允许同端账户同时在线，则生成新token返回。如果不允许则返回新生成的token并删除原token
        if (!tokenUtil.onlineMulti()) {
            Set<Object> keys = redisTemplate.keys(accessTokenKey + StringPool.COLON + "*");
            assert keys != null;
            if (!keys.isEmpty()) {
                redisTemplate.delete(keys);
            }
        }
        // 生成新的accessToken和refreshToken
        String accessToken = UUID.randomUUID().toString();
        String refreshToken = UUID.randomUUID().toString();
        token.setAccessToken(accessToken);
        token.setExpires(LocalDateTime.now().plusSeconds(tokenUtil.expireTime()));
        // redis中储存token
        redisTemplate.opsForValue().set(accessTokenKey + StringPool.COLON + accessToken, accessToken, tokenUtil.expireTime(), TimeUnit.SECONDS);
        token.setRefreshToken(refreshToken);
        redisTemplate.opsForValue().set(refreshTokenKey + StringPool.COLON + refreshToken, refreshToken, tokenUtil.refreshTokenExpireTime(), TimeUnit.SECONDS);
        // 返回Token 相关信息
        return R.ok(token);
    }
}
