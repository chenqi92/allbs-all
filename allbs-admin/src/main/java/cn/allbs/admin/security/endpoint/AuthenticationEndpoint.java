package cn.allbs.admin.security.endpoint;

import cn.allbs.admin.config.core.R;
import cn.allbs.admin.config.dto.LoginDTO;
import cn.allbs.admin.security.grant.CustomJwtToken;
import cn.allbs.admin.security.utils.TokenUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static cn.allbs.admin.config.constant.CacheConstant.CACHE_TOKEN;
import static cn.allbs.admin.security.constant.SecurityConstant.EXPIRE_TIME;

/**
 * 类 AuthenticationEndpoint
 *
 * @author ChenQi
 * @date 2024/3/19
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationEndpoint {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final RedisTemplate<Object, Object> redisTemplate;
    private final TokenUtil tokenUtil;


    @PostMapping("registerUser")
    public R<?> registerUser() {
        return R.ok();
    }

    @PostMapping("/login")
    public R<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成并返回token给客户端，后续访问携带此token
        CustomJwtToken token = new CustomJwtToken(UUID.randomUUID().toString());
        String tokenStr = tokenUtil.generateToken(authentication);
        token.setToken(tokenStr);
        token.setPermissions(authentication.getAuthorities());
        // redis中储存token
        redisTemplate.opsForValue().set(CACHE_TOKEN + tokenStr, tokenStr, EXPIRE_TIME, TimeUnit.SECONDS);
        // 返回Token 相关信息
        return R.ok(token);
    }

    @GetMapping("captcha")
    public String captcha() {
        return "123456";
    }
}
