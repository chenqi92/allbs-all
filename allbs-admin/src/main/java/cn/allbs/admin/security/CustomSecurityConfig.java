package cn.allbs.admin.security;

import cn.allbs.admin.security.filter.TokenAuthenticationFilter;
import cn.allbs.admin.security.grant.CustomDaoAuthenticationProvider;
import cn.allbs.admin.security.handler.Http401AuthenticationEntryPoint;
import cn.allbs.admin.security.handler.Http403AccessDeniedEntryPoint;
import cn.allbs.admin.security.handler.PasswordLogoutSuccessHandler;
import cn.allbs.admin.security.handler.SecurityLogoutHandler;
import cn.allbs.admin.security.properties.PermitUrlProperties;
import cn.allbs.admin.security.service.CustomUserServiceImpl;
import cn.allbs.admin.security.utils.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 类 CustomSecurityConfig
 *
 * @author ChenQi
 * @date 2024/3/8
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableConfigurationProperties(PermitUrlProperties.class)
public class CustomSecurityConfig {

    private final PermitUrlProperties permitUrlProperties;

    private final CustomUserServiceImpl customUserService;

    private final RedisTemplate<Object, Object> redisTemplate;

    private final TokenUtil tokenUtil;

    /**
     * 鉴权管理
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        CustomDaoAuthenticationProvider authProvider = new CustomDaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(customUserService);
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // @formatter:on
        return http
                // 禁用明文验证
                .httpBasic(AbstractHttpConfigurer::disable)
                // 关闭csrf
                .csrf(AbstractHttpConfigurer::disable)
                // 禁用默认登录页
                .formLogin(AbstractHttpConfigurer::disable)
                // 禁用默认登出页
                .logout(AbstractHttpConfigurer::disable)
                // 自定义需要权限验证的提示code和文字说明
                .exceptionHandling(handler -> handler.authenticationEntryPoint(new Http401AuthenticationEntryPoint()))
                // 自定义403 forbidden提示
                .exceptionHandling(handler -> handler.accessDeniedHandler(new Http403AccessDeniedEntryPoint()))
                // 禁用session
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 登出
                .logout(logout -> logout
                        .logoutUrl("/logout").addLogoutHandler(new SecurityLogoutHandler(redisTemplate, tokenUtil)).deleteCookies("rememberMe").logoutSuccessHandler(logoutSuccessHandler()))
                // 配置拦截信息
                .authorizeHttpRequests(authorization -> authorization
                        // 跨域允许所有的OPTIONS请求
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // 权限操作相关接口
                        .requestMatchers("/auth/**").permitAll()
                        // 放行白名单
                        .requestMatchers(permitUrlProperties.getIgnoreUrls()
                                .stream()
                                .map(AntPathRequestMatcher::new)
                                .toList()
                                .toArray(new AntPathRequestMatcher[0])).permitAll()
                        .requestMatchers(permitUrlProperties.getMatchers().toArray(new RequestMatcher[0])).permitAll()
                        .anyRequest().authenticated()
                )
                // 自定义认证处理
                .authenticationProvider(authenticationProvider())
                // 注册自定义拦截器
                .addFilterBefore(new TokenAuthenticationFilter(customUserService, redisTemplate, tokenUtil), UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    /**
     * 登出成功处理方法
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new PasswordLogoutSuccessHandler();
    }

    /**
     * 加密方式
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
