package cn.allbs.admin.security.service;

import cn.allbs.admin.config.core.R;
import cn.allbs.admin.security.grant.CustomJwtToken;
import cn.allbs.admin.security.model.LoginRequest;
import cn.allbs.admin.security.model.UserNameLoginRequest;
import cn.allbs.admin.security.utils.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static cn.allbs.admin.config.constant.CacheConstant.CACHE_TOKEN;

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
        // 首先解码
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userNameRequest.getUsername(), userNameRequest.getPassword())
        );
        String tokenKey = CACHE_TOKEN + userNameRequest.getUsername();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成并返回token给客户端，后续访问携带此token
        CustomJwtToken token = new CustomJwtToken(UUID.randomUUID().toString());
        token.setPermissions(authentication.getAuthorities());
        // 查看是否会踢同用户下线 如果允许直接返回已存在的token，如果不允许则生成新token
        Object redisToken = redisTemplate.opsForValue().get(tokenKey);
        if (tokenUtil.onlineMulti() && !ObjectUtils.isEmpty(redisToken)) {
            token.setToken(redisToken.toString());
            return R.ok(token);
        }
        redisTemplate.delete(tokenKey);
        String tokenStr = tokenUtil.generateToken(authentication);
        token.setToken(tokenStr);
        // redis中储存token
        redisTemplate.opsForValue().set(tokenKey, tokenStr, tokenUtil.expireTime(), TimeUnit.SECONDS);
        // 返回Token 相关信息
        return R.ok(token);
    }
}
