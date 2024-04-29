package cn.allbs.admin.security.grant;

import cn.allbs.admin.security.exception.UserNameNotFoundException;
import cn.allbs.admin.security.model.SysUser;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;

/**
 * 类 CustomDaoAuthenticationProvider
 *
 * @author ChenQi
 * @date 2024/3/19
 */
@Slf4j
public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {

    /**
     * user 属性校验
     */
    @Setter
    private UserDetailsChecker preAuthenticationChecks = new AccountStatusUserDetailsChecker();

    private UserDetailsPasswordService userDetailsPasswordService;

    public CustomDaoAuthenticationProvider() {
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 可以在此处覆写整个登录认证逻辑
        if (authentication.getCredentials() == null) {
            log.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException("Bad credentials");
        }

        // 手机号
        String userName = authentication.getName();
        SysUser userDetails = (SysUser) this.getUserDetailsService().loadUserByUsername(userName);

        // 校验账号是否禁用
        preAuthenticationChecks.check(userDetails);

        // 账号密码校验
        additionalAuthenticationChecks(userDetails,
                (UsernamePasswordAuthenticationToken) authentication);

        // 提供用户名、密码、权限列表供SecurityLoginFilter使用
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        // 可以在此覆写整个密码校验逻辑
        if (authentication.getCredentials() == null) {
            this.logger.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException(this.messages
                    .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
        String presentedPassword = authentication.getCredentials().toString();
        if (!this.getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
            this.logger.debug("Failed to authenticate since password does not match stored value");
            throw new UserNameNotFoundException("用户名或密码错误");
        }
    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
                                                         UserDetails user) {
        boolean upgradeEncoding = this.userDetailsPasswordService != null
                && this.getPasswordEncoder().upgradeEncoding(user.getPassword());
        if (upgradeEncoding) {
            String presentedPassword = authentication.getCredentials().toString();
            String newPassword = this.getPasswordEncoder().encode(presentedPassword);
            user = this.userDetailsPasswordService.updatePassword(user, newPassword);
        }
        return super.createSuccessAuthentication(principal, authentication, user);
    }
}
