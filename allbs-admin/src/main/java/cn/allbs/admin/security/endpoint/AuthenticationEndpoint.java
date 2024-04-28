package cn.allbs.admin.security.endpoint;

import cn.allbs.admin.config.core.R;
import cn.allbs.admin.security.model.LoginRequest;
import cn.allbs.admin.security.service.LoginService;
import cn.allbs.admin.security.service.LoginServiceFactory;
import cn.allbs.admin.security.utils.TokenUtil;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    private final LoginServiceFactory loginServiceFactory;

    /**
     * 用户注册
     *
     * @return R<?>
     */
    @PostMapping("registerUser")
    public R<?> registerUser() {
        return R.ok();
    }

    /**
     * 用户登录
     *
     * @param request request
     * @return R<?>
     */
    @PostMapping("/login")
    public R<?> login(@RequestBody LoginRequest request) {
        LoginService service = loginServiceFactory.getLoginService(request.getLoginType());
        return service.authenticate(request);
    }

    /**
     * 验证码
     *
     * @return R<?>
     */
    @GetMapping("captcha")
    public void captcha() {
    }
}
