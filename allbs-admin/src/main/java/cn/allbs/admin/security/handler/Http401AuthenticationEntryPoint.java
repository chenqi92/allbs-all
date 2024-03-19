package cn.allbs.admin.security.handler;

import cn.allbs.admin.config.core.R;
import cn.allbs.admin.security.enums.SystemCode;
import cn.allbs.admin.security.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * 类 Http401AuthenticationEntryPoint
 *
 * @author ChenQi
 * @date 2024/3/18
 */
public class Http401AuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        ResponseUtil.out(response, SystemCode.FORBIDDEN_401);
    }
}

