package cn.allbs.admin.config.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 类 LoginDTO 用户登录信息
 *
 * @author ChenQi
 * @date 2024/3/19
 */
@Data
@Schema(title = "用户登录信息", name = "LoginDTO")
public class LoginDTO {

    /**
     * 用户名
     */
    @NotBlank
    @Schema(description = "用户名", name = "userName", implementation = String.class, example = "张三")
    private String userName;

    /**
     * 密码
     */
    @NotBlank
    @Schema(description = "加密后密码", name = "password", implementation = String.class)
    private String password;

    /**
     * 验证码
     */
    private String captcha;
}
