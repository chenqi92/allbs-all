package cn.allbs.admin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static cn.allbs.admin.config.constant.CommonConstants.SPRING_APPLICATION_NAME;

/**
 * 类 StartedEventListener 控制台日志打印
 *
 * @author ChenQi
 * @date 2024/2/29
 */
@Slf4j
@Component
public class StartedEventListener {

    @Async
    @Order(Ordered.LOWEST_PRECEDENCE - 1)
    @EventListener(WebServerInitializedEvent.class)
    public void afterStart(WebServerInitializedEvent event) {
        WebServerApplicationContext context = event.getApplicationContext();
        Environment environment = context.getEnvironment();
        String appName = environment.getRequiredProperty(SPRING_APPLICATION_NAME);
        int localPort = event.getWebServer().getPort();
        String localIp = "localhost";
        try {
            localIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ignored) {
        }
        String profile = StringUtils.arrayToCommaDelimitedString(environment.getActiveProfiles());
        StringBuilder sb = new StringBuilder();
        sb.append("\n-----------------------------系统信息-------------------------------\n");
        sb.append(String.format("当前服务%s启动完成,使用的端口:%s,环境变量:%s\n", appName, localPort, profile));
        // 如果有 swagger，打印开发阶段的 swagger ui 地址
        // 判断是否是在prod环境下
        if (!"prod".equals(profile)) {
            if (ClassUtils.isPresent("springfox.documentation.spring.web.plugins.Docket", null) || ClassUtils.isPresent("io.swagger.v3.oas.models.OpenAPI", null)) {
                sb.append(String.format("当前服务接口文档访问路径为[http://%s:%s/doc.html]\n", localIp, localPort));
            } else {
                sb.append(String.format("当前服务uri基础路径为[http://%s:%s]\n", localIp, localPort));
            }
            sb.append("-----------------------------系统信息-------------------------------\n");
            log.info(sb.toString());
        }
    }
}
