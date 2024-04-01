package cn.allbs.admin.config.advice;

import cn.allbs.admin.config.core.R;
import cn.allbs.admin.config.exception.ServiceException;
import cn.allbs.admin.config.utils.BeanUtil;
import cn.allbs.admin.security.enums.SystemCode;
import jakarta.servlet.RequestDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.Optional;

/**
 * 类 AllbsErrorAttributes
 *
 * @author ChenQi
 * @date 2024/4/1
 */
@Slf4j
@SuppressWarnings("unchecked")
public class AllbsErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        // 请求地址
        String requestUrl = this.getAttr(webRequest, RequestDispatcher.ERROR_REQUEST_URI);
        if (!StringUtils.hasText(requestUrl)) {
            requestUrl = this.getAttr(webRequest, RequestDispatcher.FORWARD_REQUEST_URI);
        }
        // status code
        Integer status = this.getAttr(webRequest, RequestDispatcher.ERROR_STATUS_CODE);
        // error
        Throwable error = getError(webRequest);
        log.error("URL:{} error status:{}", requestUrl, status, error);
        R<Object> result;
        if (error instanceof ServiceException) {
            result = ((ServiceException) error).getResult();
            result = Optional.ofNullable(result).orElse(R.fail(SystemCode.FAILURE));
        } else {
            result = R.fail(Optional.ofNullable(status).orElse(SystemCode.FAILURE.getCode()), String.format("接口异常，请联系开发人员并提供接口名称[%s]及异常状态值[%s]", requestUrl, status));
        }
        return BeanUtil.toMap(result);
    }

    @Nullable
    private <T> T getAttr(WebRequest webRequest, String name) {
        return (T) webRequest.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }
}
