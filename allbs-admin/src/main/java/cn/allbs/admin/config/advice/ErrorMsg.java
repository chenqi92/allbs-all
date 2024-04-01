package cn.allbs.admin.config.advice;

import lombok.Builder;
import lombok.Data;

/**
 * 类 ErrorMsg
 *
 * @author ChenQi
 * @date 2024/4/1
 */
@Builder
@Data
public class ErrorMsg {

    /**
     * 错误信息主键
     */
    private String uuid;

    /**
     * 错误信息提示
     */
    private String msg;
}
