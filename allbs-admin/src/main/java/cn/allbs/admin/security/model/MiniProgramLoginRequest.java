package cn.allbs.admin.security.model;

import cn.allbs.admin.security.enums.LoginType;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 类 MiniProgramLoginRequest
 *
 * @author ChenQi
 * @date 2024/4/28
 */
@Data
@JsonTypeName("miniProgram")
@RequiredArgsConstructor
public class MiniProgramLoginRequest implements LoginRequest {

    @Override
    public LoginType getLoginType() {
        return LoginType.MINI_PROGRAM;
    }
}
