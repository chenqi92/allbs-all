package cn.allbs.admin.config.advice;

import cn.allbs.admin.config.annotation.IgnoreAdvice;
import cn.allbs.admin.config.core.R;
import cn.allbs.admin.config.utils.BeanUtil;
import cn.allbs.admin.security.enums.SystemCode;
import cn.allbs.admin.security.properties.PermitUrlProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 类 ResponseControllerAdvice 统一返回结果
 *
 * @author ChenQi
 * @date 2024/4/1
 */
@RestControllerAdvice
@AutoConfigureAfter(PermitUrlProperties.class)
@RequiredArgsConstructor
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    private final PermitUrlProperties ignoreUrlProperties;

    private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");

    @Getter
    @Setter
    private List<String> ignoreUrls = new ArrayList<>();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果接口返回的类型本身就是R那就没有必要进行额外的操作，返回false
        return !Objects.requireNonNull(returnType.getMethod()).getReturnType().equals(R.class);
    }

    /**
     * 判断url是否需要拦截
     *
     * @param uri
     * @return
     */
    private boolean ignoring(String uri) {
        // 添加安全中过滤的所有uri
        ignoreUrlProperties.getIgnoreUrls().addAll(ignoreUrls);
        return ignoreUrlProperties.getIgnoreUrls().stream().anyMatch(a -> isMatch(a, uri));
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (data == null) {
            return R.ok(new ArrayList<>());
        }
        //判断url是否需要拦截
        if (this.ignoring(serverHttpRequest.getURI().getPath())) {
            return data;
        }
        if (returnType.hasMethodAnnotation(IgnoreAdvice.class)) {
            return data;
        }
        if (data instanceof Map) {
            Map<String, Object> map = BeanUtil.toMap(data);
            if (map.containsKey("code") && map.containsKey("msg") && map.containsKey("data")) {
                return data;
            }
        }
        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(R.ok(data));
            } catch (JsonProcessingException e) {
                return R.fail(SystemCode.FAILURE);
            }
        }
        // 将原本的数据包装在R里 默认为空数据
        return R.ok(data);
    }

    /**
     * 判断url是否与规则配置:
     * ? 表示单个字符
     * * 表示一层路径内的任意字符串，不可跨层级
     * ** 表示任意层路径
     *
     * @param url     匹配规则
     * @param urlPath 需要匹配的url
     * @return
     */
    public static boolean isMatch(String url, String urlPath) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(url, urlPath);
    }
}
