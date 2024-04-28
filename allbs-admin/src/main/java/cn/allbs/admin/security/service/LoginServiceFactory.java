package cn.allbs.admin.security.service;

import cn.allbs.admin.security.enums.LoginType;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 类 LoginServiceFactory
 *
 * @author ChenQi
 * @date 2024/4/28
 */
@Component
public class LoginServiceFactory {
    @Resource
    private UsernamePasswordLoginService usernamePasswordLoginService;
    @Resource
    private MobileLoginService mobileLoginService;
    @Resource
    private EmailLoginService emailLoginService;
    @Resource
    private MiniProgramLoginService miniProgramLoginService;

    /**
     * 获取登录服务
     *
     * @param type 登录类型
     * @return 登录服务
     */
    public LoginService getLoginService(LoginType type) {
        return switch (type) {
            case USERNAME -> usernamePasswordLoginService;
            case MOBILE -> mobileLoginService;
            case EMAIL -> emailLoginService;
            case MINI_PROGRAM -> miniProgramLoginService;
            default -> throw new IllegalArgumentException("无效的登录类型: " + type);
        };
    }
}
