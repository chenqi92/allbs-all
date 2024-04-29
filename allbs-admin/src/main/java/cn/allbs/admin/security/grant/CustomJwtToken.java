package cn.allbs.admin.security.grant;

import cn.allbs.admin.security.model.SysUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import static cn.allbs.admin.security.constant.SecurityConstant.BEARER_TYPE;

/**
 * 自定义登录后返回的token格式及内容
 *
 * @author ChenQi
 * @date 2024/3/19
 */
@Getter
public class CustomJwtToken implements Serializable {

    @Serial
    private static final long serialVersionUID = 2149134569530465633L;

    /**
     * -- GETTER --
     * The token value.
     *
     * @return The token value.
     */
    @Setter
    @JsonIgnore
    private String value;

    /**
     * accessToken
     */
    @Setter
    private String accessToken;

    /**
     * refreshToken
     */
    @Setter
    private String refreshToken;

    /**
     * 用户名
     */
    @Setter
    private String username;

    /**
     * 头像
     */
    @Setter
    private String avatar;

    /**
     * 昵称
     */
    @Setter
    private String nickname;

    /**
     * 角色列表
     */
    @Setter
    private String[] roles;

    /**
     * 过期时间
     */
    @Setter
    private LocalDateTime expires;

    private final String tokenType = BEARER_TYPE.toLowerCase();

    public CustomJwtToken(String value, SysUser sysUser) {
        this.value = value;
        this.avatar = sysUser.getAvatar();
        this.nickname = sysUser.getNickname();
        this.roles = sysUser.getRoles();
    }

    @SuppressWarnings("unused")
    private CustomJwtToken() {
        this((String) null);
    }

    public CustomJwtToken(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && toString().equals(obj.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
}
