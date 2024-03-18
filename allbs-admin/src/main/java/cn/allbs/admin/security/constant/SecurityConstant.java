package cn.allbs.admin.security.constant;

/**
 * security常量
 *
 * @author ChenQi
 * @date 2024/3/8
 */
public interface SecurityConstant {

    /**
     * 签名
     */
    String SIGNING_KEY = "allbs-sign";

    /**
     * 正常用户
     */
    Integer STATUS_NORMAL = 0;

    /**
     * 冻结
     */
    Integer STATUS_LOCK = 9;

    /**
     * 角色前缀
     */
    String ROLE = "ROLE_";

    /**
     * Bearer
     */
    String BEARER_TYPE = "Bearer ";

    /**
     * token
     */
    String TOKEN = "token";

    /**
     * 创建时间
     */
    String CREATED_TIME = "createTime";

    /**
     * 过期时间
     */
    Long EXPIRE_TIME = 7200L;

    /**
     * 刷新时间
     */
    Long REFRESH_TIME = 43200L;

    /**
     * 签名
     */
    String SIGN_KEY = "allbs";
}
