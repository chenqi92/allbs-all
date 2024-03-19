package cn.allbs.admin.security.handler;

import cn.allbs.admin.security.enums.SystemCode;
import cn.allbs.admin.security.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * 类 Http403AccessDeniedEntryPoint
 *
 * @author ChenQi
 * @date 2024/3/18
 */
public class Http403AccessDeniedEntryPoint implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        ResponseUtil.write(response, SystemCode.FORBIDDEN_403);
    }
}
