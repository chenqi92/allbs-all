package cn.allbs.admin.security.service;

import cn.allbs.admin.config.core.R;
import cn.allbs.admin.security.model.LoginRequest;

/**
 * 登录接口
 *
 * @author ChenQi
 * @date 2024/4/28
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param request 登录请求
     * @return 登录结果
     */
    R<?> authenticate(LoginRequest request);
}
