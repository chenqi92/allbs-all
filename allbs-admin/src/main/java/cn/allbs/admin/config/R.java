package cn.allbs.admin.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

import static cn.allbs.admin.config.constant.CommonConstants.*;

/**
 * 类 R 通用返回封装
 *
 * @author ChenQi
 * @date 2024/2/26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回提示信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public static <T> R<T> ok() {
        return restResult(null, SUCCESS, SUCCESS_MSG);
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, SUCCESS, SUCCESS_MSG);
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> R<T> ok(T data, String msg, int code) {
        return restResult(data, code, msg);
    }

    public static <T> R<T> failed() {
        return restResult(null, FAIL, FAILED_MSG);
    }

    public static <T> R<T> failed(String msg) {
        return restResult(null, FAIL, msg);
    }

    public static <T> R<T> failed(T data) {
        return restResult(data, FAIL, FAILED_MSG);
    }

    public static <T> R<T> failed(T data, String msg) {
        return restResult(data, FAIL, msg);
    }

    public static <T> R<T> failed(T data, String msg, int code) {
        return restResult(data, code, msg);
    }

    static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public boolean isOk() {
        return this.code == SUCCESS;
    }
}
