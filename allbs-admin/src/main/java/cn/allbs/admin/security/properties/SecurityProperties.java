package cn.allbs.admin.security.properties;

/**
 * 类 SecurityProperties
 *
 * @author ChenQi
 * @date 2024/3/8
 */

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Slf4j
@Configuration
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class SecurityProperties {

    /**
     * 过期时间 单位秒
     */
    private Long expireTime = 43200L;

    /**
     * 刷新时间 单位秒
     */
    private Long refreshTime;

    /**
     * 自定义签名
     */
    private String signKey;
}
