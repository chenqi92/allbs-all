package cn.allbs.admin.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 类 PasswordLogoutSuccessHandler 登出成功后执行的操作
 *
 * @author ChenQi
 * @date 2024/3/15
 */
@Slf4j
public class PasswordLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication == null) {
            return;
        }
        // 获取请求参数中是否包含 回调地址
        log.info("IP {}，用户 {}， 于 {} 退出系统。", request.getRemoteHost(), authentication.getName(), LocalDateTime.now());
        // 记录日志
        // 发送邮件提醒
    }
}
