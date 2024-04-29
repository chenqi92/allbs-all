package cn.allbs.admin.security.utils;

import cn.allbs.admin.security.constant.SecurityConstant;
import cn.allbs.admin.security.properties.TokenProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import static cn.allbs.admin.security.constant.SecurityConstant.CREATED_TIME;
import static cn.allbs.admin.security.constant.SecurityConstant.CURRENT_PRODUCT;

/**
 * 类 TokenUtil
 *
 * @author ChenQi
 * @date 2024/3/8
 */
@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnBean(TokenProperties.class)
public class TokenUtil {

    private final TokenProperties tokenProperties;


    /**
     * 生成令牌
     *
     * @param authentication 用户
     * @return 令牌
     */
    public String generateToken(Authentication authentication) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put(CREATED_TIME, new Date());
        return generateToken(claims, SecurityUtil.getUsername(authentication));
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims  数据声明
     * @param subject subject
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims, String subject) {
        Date expirationDate = new Date(System.currentTimeMillis() + tokenProperties.getExpiredTime() * 1000);
        return Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuer(CURRENT_PRODUCT)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .signWith(getSignKey())
                .compact();
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token).getPayload();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 验证令牌
     *
     * @param token    token
     * @param username 用户名
     * @return token中得用户名是否和待验证用户名一致&token是否过期
     */
    public Boolean validateToken(String token, String username) {
        String userName = getUsernameFromToken(token);
        return (userName.equals(username) && !isTokenExpired(token));
    }

    /**
     * 验证 token，返回结果
     *
     * <p>
     * 如果解析失败，说明 token 是无效的
     */
    public boolean validateToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        try {
            Jwts.parser()
                    .verifyWith(getSignKey()).build().parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.warn("Request to parse expired JWT : {} failed : {}", token, e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.warn("Request to parse unsupported JWT : {} failed : {}", token, e.getMessage());
        } catch (MalformedJwtException e) {
            log.warn("Request to parse invalid JWT : {} failed : {}", token, e.getMessage());
        } catch (IllegalArgumentException e) {
            log.warn("Request to parse empty or null JWT : {} failed : {}", token, e.getMessage());
        }
        return false;
    }

    /**
     * 刷新令牌
     *
     * @param token token
     * @return refreshedToken
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CREATED_TIME, new Date());
            refreshedToken = generateToken(claims, claims.getSubject());
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取请求token
     *
     * @param request HttpServletRequest
     * @return token
     */
    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null) {
            token = request.getHeader(SecurityConstant.TOKEN);
        } else if (token.startsWith(SecurityConstant.BEARER_TYPE)) {
            token = token.substring(SecurityConstant.BEARER_TYPE.length());
        }
        if ("".equals(token)) {
            token = null;
        }
        return token;
    }

    /**
     * 查询token中得指定负载参数
     *
     * @param token          token
     * @param claimsResolver 负载类型
     * @param <T>            负载类
     * @return 指定负载参数对应得值
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 查询所有负载值
     *
     * @param token token
     * @return Claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * 获取签名Key
     *
     * @return 签名
     */
    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(tokenProperties.getSignKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 获取过期时间
     *
     * @return 过期时间
     */
    public Long expireTime() {
        return tokenProperties.getExpiredTime();
    }

    /**
     * 获取刷新时间
     *
     * @return 刷新时间
     */
    public Long refreshTokenExpireTime() {
        return tokenProperties.getRefreshExpiredTime();
    }

    /**
     * 是否允许同端账户同时在线 true 允许 false 不允许
     *
     * @return boolean
     */
    public boolean onlineMulti() {
        return tokenProperties.getMultiOnline();
    }
}
