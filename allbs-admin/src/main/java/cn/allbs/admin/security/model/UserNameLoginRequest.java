package cn.allbs.admin.security.model;

import cn.allbs.admin.security.enums.LoginType;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 类 UserNameLoginRequest
 *
 * @author ChenQi
 * @date 2024/4/28
 */
@Data
@JsonTypeName("username")
@RequiredArgsConstructor
public class UserNameLoginRequest implements LoginRequest {

    @NotBlank
    @Schema(description = "用户名", name = "userName", implementation = String.class, example = "admin")
    private String username;
    /**
     * 密码
     */
    @NotBlank
    @Schema(description = "加密后密码", name = "password", implementation = String.class)
    private String password;

    @Override
    public LoginType getLoginType() {
        return LoginType.USERNAME;
    }
}
