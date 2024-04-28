package cn.allbs.admin.security.service;

import cn.allbs.admin.config.core.R;
import cn.allbs.admin.security.model.LoginRequest;
import cn.allbs.admin.security.model.MobileLoginRequest;
import org.springframework.stereotype.Service;

/**
 * 类 MobileLoginService
 *
 * @author ChenQi
 * @date 2024/4/28
 */
@Service
public class MobileLoginService implements LoginService {

    @Override
    public R<?> authenticate(LoginRequest request) {
        MobileLoginRequest emailRequest = (MobileLoginRequest) request;
        // 这里添加实际的验证逻辑
        return R.ok("登录成功");
    }
}
