package cn.allbs.admin.security.utils;

import cn.allbs.admin.config.core.R;
import cn.allbs.admin.security.enums.IResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 类 ResponseUtil
 *
 * @author ChenQi
 * @date 2024/3/15
 */
@Slf4j
public class ResponseUtil {

    public static void out(@NotNull HttpServletResponse response, R<?> r) {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(r.getCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try {
            objectMapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    /**
     * 输出信息到浏览器 不带错误信息
     *
     * @param response
     * @param data
     * @throws IOException
     */
    public static void write(@NotNull HttpServletResponse response, IResultCode data) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        R result = R.ok(null, data.getMsg(), data.getCode());
        String json = JsonUtil.toJson(result);
        response.getWriter().print(json);
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * 不带R的返回信息
     *
     * @param response
     * @param data
     * @throws IOException
     */
    public static void writeWithoutR(@NotNull HttpServletResponse response, Object data) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        String json = JsonUtil.toJson(data);
        response.getWriter().print(json);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
