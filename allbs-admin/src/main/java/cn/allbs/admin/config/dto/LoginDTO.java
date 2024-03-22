package cn.allbs.admin.config.dto;

import cn.allbs.admin.security.enums.ClientEnum;
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
     * 区分登录端
     */
    @NotBlank
    @Schema(description = "登录端", name = "client", implementation = ClientEnum.class, type = "int32", allowableValues = {"1", "2", "3"})
    private ClientEnum client;

    /**
     * 设备编码
     */
    @Schema(description = "当使用app登录时，设备唯一码", name = "clientCode", implementation = String.class)
    private String clientCode;

    /**
     * 验证码
     */
    private String captcha;
}
