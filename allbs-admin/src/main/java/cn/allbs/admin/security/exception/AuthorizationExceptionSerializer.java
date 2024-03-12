package cn.allbs.admin.security.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * 类 AuthorizationExceptionSerializer
 *
 * @author ChenQi
 * @date 2024/3/12
 */
public class AuthorizationExceptionSerializer extends StdSerializer<AuthorizationException> {

    public AuthorizationExceptionSerializer() {
        super(AuthorizationException.class);
    }

    @Override
    public void serialize(AuthorizationException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("code", value.getErrorCode());
        gen.writeStringField("msg", value.getMessage());
        gen.writeStringField("data", null);
        gen.writeEndObject();
    }
}
