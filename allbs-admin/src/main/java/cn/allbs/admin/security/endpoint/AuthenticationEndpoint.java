package cn.allbs.admin.security.endpoint;

import cn.allbs.admin.config.core.R;
import cn.allbs.admin.config.dto.LoginDTO;
import cn.allbs.admin.security.grant.CustomJwtToken;
import cn.allbs.admin.security.utils.TokenUtil;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static cn.allbs.admin.config.constant.CacheConstant.CACHE_TOKEN;

/**
 * 类 AuthenticationEndpoint
 *
 * @author ChenQi
 * @date 2024/3/19
 */
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "授权相关接口", description = "登录、登出、验证码等")
@OpenAPIDefinition(info = @Info(title = "授权相关Api", version = "v1", description = "登录、登出、验证码等功能"))
public class AuthenticationEndpoint {

    private final AuthenticationManager authenticationManager;
    private final RedisTemplate<Object, Object> redisTemplate;
    private final TokenUtil tokenUtil;


    @PostMapping("registerUser")
    public R<?> registerUser() {
        return R.ok();
    }

    @PostMapping("/login")
    public R<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        // 首先解码
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 查看是否会踢同用户下线
        // 生成并返回token给客户端，后续访问携带此token
        CustomJwtToken token = new CustomJwtToken(UUID.randomUUID().toString());
        String tokenStr = tokenUtil.generateToken(authentication);
        token.setToken(tokenStr);
        token.setPermissions(authentication.getAuthorities());
        // redis中储存token
        redisTemplate.opsForValue().set(CACHE_TOKEN + tokenStr, tokenStr, tokenUtil.expireTime(), TimeUnit.SECONDS);
        // 返回Token 相关信息
        return R.ok(token);
    }

    @GetMapping("captcha")
    public String captcha() {
        return "123456";
    }
}
