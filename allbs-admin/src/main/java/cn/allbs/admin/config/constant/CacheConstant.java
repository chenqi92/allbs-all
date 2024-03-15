package cn.allbs.admin.config.constant;

/**
 * 接口 CacheConstant redis缓存key 常量
 *
 * @author ChenQi
 * @date 2024/3/15
 */
public interface CacheConstant {

    String PRODUCT = "allbs:";

    /**
     * token信息
     */
    String CACHE_TOKEN = PRODUCT + "token:";
}
