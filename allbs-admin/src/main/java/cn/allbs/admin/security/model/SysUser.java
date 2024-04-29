package cn.allbs.admin.security.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

import java.io.Serial;
import java.util.Collection;

/**
 * 类 SysUser
 *
 * @author ChenQi
 * @date 2024/3/8
 */
@Getter
public class SysUser extends User {

    /**
     * 用户ID
     */
    private final Long id;
    /**
     * 昵称
     */
    private final String nickname;

    /**
     * 头像
     */
    private final String avatar;

    private final String[] roles;

    @Serial
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    public SysUser(Long id, String nickname, String avatar, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String[] roles) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.nickname = nickname;
        this.avatar = avatar;
        this.roles = roles;
    }
}
