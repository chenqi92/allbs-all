package cn.allbs.admin.security.properties;

import cn.allbs.admin.security.annotation.IgnoreUri;
import cn.allbs.admin.security.utils.SpringContextHolder;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 类 PermitAllUrlProperties
 *
 * @author ChenQi
 * @date 2024/3/8
 */
@Slf4j
@Getter
@ConditionalOnExpression("!'${security.ignore-urls}'.isEmpty()")
@ConfigurationProperties(prefix = "security")
public class PermitUrlProperties implements InitializingBean {

    private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");

    @Setter
    @NestedConfigurationProperty
    private List<String> ignoreUrls = new ArrayList<>();


    private final List<RequestMatcher> matchers = new ArrayList<>();

    @Override
    public void afterPropertiesSet() {
        RequestMappingHandlerMapping mapping = SpringContextHolder.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        for (RequestMappingInfo info : map.keySet()) {
            HandlerMethod handlerMethod = map.get(info);

            // 获取类注解
            IgnoreUri controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), IgnoreUri.class);

            // 获取方法注解
            if (controller == null) {
                IgnoreUri method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), IgnoreUri.class);
                Optional.ofNullable(method).ifPresent(inner -> {
                    assert info.getPathPatternsCondition() != null;
                    info.getPathPatternsCondition().getPatterns()
                            .forEach(url -> this.filterPath(url.getPatternString(), info, map));
                });
                continue;
            }

            Class<?> beanType = handlerMethod.getBeanType();
            Method[] methods = beanType.getDeclaredMethods();
            Method method = handlerMethod.getMethod();
            if (ArrayUtils.contains(methods, method)) {
                assert info.getPathPatternsCondition() != null;
                info.getPathPatternsCondition().getPatterns()
                        .forEach(url -> this.filterPath(url.getPatternString(), info, map));
            }
        }
    }

    private void filterPath(String url, RequestMappingInfo info, Map<RequestMappingInfo, HandlerMethod> map) {
        List<String> methodList = info.getMethodsCondition().getMethods().stream().map(RequestMethod::name)
                .collect(Collectors.toList());
        String resultUrl = RegExUtils.replaceAll(url, PATTERN, "*");
        if (!CollectionUtils.isEmpty(methodList)) {
            matchers.add(AntPathRequestMatcher.antMatcher(HttpMethod.valueOf(StringUtils.join(methodList, StringPool.COMMA)), resultUrl));
        }
    }
}
