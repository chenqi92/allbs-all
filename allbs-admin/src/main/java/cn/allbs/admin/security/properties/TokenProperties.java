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
     * access-token过期时间（秒）
     */
    private Long expiredTime = 7200L;

    /**
     * refresh-token过期时间（秒）
     */
    private Long refreshExpiredTime = 604800L;

    /**
     * 签名
     */
    private String signKey;

    /**
     * 是否允许多端登录
     */
    private Boolean multiOnline = true;
}
