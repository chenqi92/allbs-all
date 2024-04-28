package cn.allbs.admin.security.service;

import cn.allbs.admin.config.core.R;
import cn.allbs.admin.security.model.LoginRequest;
import cn.allbs.admin.security.model.MiniProgramLoginRequest;
import org.springframework.stereotype.Service;

/**
 * 类 MiniProgramLoginService 微信小程序的登录实现
 *
 * @author ChenQi
 * @date 2024/4/28
 */
@Service
public class MiniProgramLoginService implements LoginService {
    @Override
    public R<?> authenticate(LoginRequest request) {
        MiniProgramLoginRequest emailRequest = (MiniProgramLoginRequest) request;
        // 这里添加实际的验证逻辑
        return R.ok("登录成功");
    }
}
