package cn.allbs.admin.config.context;

import java.util.function.Function;

/**
 * 类 DefaultAllbsContext
 *
 * @author ChenQi
 * @date 2024/4/1
 */
public class DefaultAllbsContext implements IContext {

    @Override
    public String getRequestId() {
        return null;
    }

    @Override
    public String getTenantId() {
        return null;
    }

    @Override
    public String getAccountId() {
        return null;
    }

    @Override
    public String get(String ctxKey) {
        return null;
    }

    @Override
    public <T> T get(String ctxKey, Function<String, T> function) {
        return null;
    }
}
