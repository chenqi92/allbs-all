package cn.allbs.admin.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 类 TokenProperties
 *
 * @author ChenQi
 * @date 2024/3/20
 */
@Data
@Component
@ConfigurationProperties(prefix = "token")
public class TokenProperties {

    /**
     * 过期时间
     */
    private Long expiredTime = 7200L;

    /**
     * 刷新时间
     */
    private Long refreshTime = 259200L;

    /**
     * 签名
     */
    private String signKey;

    /**
     * 是否允许多端登录
     */
    private Boolean multiOnline = true;
}
