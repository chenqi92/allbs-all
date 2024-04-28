package cn.allbs.admin.security.model;

import cn.allbs.admin.security.enums.LoginType;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 类 EmailLoginRequest
 *
 * @author ChenQi
 * @date 2024/4/28
 */
@Data
@JsonTypeName("email")
@RequiredArgsConstructor
public class EmailLoginRequest implements LoginRequest {

    @NotBlank
    @Email(message = "邮箱格式不正确")
    @Schema(description = "邮箱", name = "email", implementation = String.class, example = "xx@xx.com")
    private String email;

    @NotBlank
    @Schema(description = "验证码", name = "code", implementation = String.class, example = "1234")
    private String code;

    @Override
    public LoginType getLoginType() {
        return LoginType.EMAIL;
    }
}
