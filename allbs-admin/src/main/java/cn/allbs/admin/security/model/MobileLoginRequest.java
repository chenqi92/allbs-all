package cn.allbs.admin.security.model;

import cn.allbs.admin.security.enums.LoginType;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 类 MobileLoginRequest 手机登录接收类
 *
 * @author ChenQi
 * @date 2024/4/28
 */
@Data
@JsonTypeName("mobile")
@RequiredArgsConstructor
public class MobileLoginRequest implements LoginRequest {

    @NotBlank
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    @Schema(description = "手机号", name = "mobile", implementation = String.class, example = "13112345678")
    private String mobile;

    @NotBlank
    @Schema(description = "验证码", name = "code", implementation = String.class, example = "123456")
    private String code;
    /**
     * 设备编码
     */
    @Schema(description = "移动设备唯一码", name = "clientCode", implementation = String.class)
    private String clientCode;

    @Override
    public LoginType getLoginType() {
        return LoginType.MOBILE;
    }
}
