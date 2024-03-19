package cn.allbs.admin.security.grant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

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
     * token
     */
    @Setter
    private String token;

    private final String tokenType = BEARER_TYPE.toLowerCase();

    /**
     * 权限集合
     */
    private Set<String> permissions;

    public CustomJwtToken(String value) {
        this.value = value;
    }

    @SuppressWarnings("unused")
    private CustomJwtToken() {
        this((String) null);
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

    public void setPermissions(Collection<? extends GrantedAuthority> authorities) {
        this.permissions = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }
}
