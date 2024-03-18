package cn.allbs.admin.security.handler;

import cn.allbs.admin.config.core.R;
import cn.allbs.admin.security.enums.SystemCode;
import cn.allbs.admin.security.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 类 Http403AccessDeniedEntryPoint
 *
 * @author ChenQi
 * @date 2024/3/18
 */
public class Http403AccessDeniedEntryPoint implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        ResponseUtil.out(response, R.fail(SystemCode.FORBIDDEN_403));
    }
}
