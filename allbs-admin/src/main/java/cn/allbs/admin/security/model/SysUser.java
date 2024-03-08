package cn.allbs.admin.security.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

import java.io.Serial;
import java.util.Collection;
import java.util.Set;

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
     * 手机号
     */
    private final String phone;

    /**
     * 头像
     */
    private final String avatar;

    private final Set<Long> entIdList;

    @Serial
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    public SysUser(Long id, String phone, String avatar, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Set<Long> entIdList) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.phone = phone;
        this.avatar = avatar;
        this.entIdList = entIdList;
    }
}
