package cn.allbs.admin.config.customizer;

import cn.allbs.admin.config.enums.BasicEnum;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.ParameterCustomizer;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;

/**
 * 类 ApiEnumParameterCustomizer 枚举参数自定义配置
 *
 * @author ChenQi
 * @date 2024/2/29
 */
@Component
public class ApiEnumParameterCustomizer implements ParameterCustomizer, BasicEnumCustomizer {

    @Override
    public Parameter customize(Parameter parameterModel, MethodParameter methodParameter) {
        Class<?> parameterType = methodParameter.getParameterType();

        // 枚举处理
        if (BasicEnum.class.isAssignableFrom(parameterType)) {
            parameterModel.setDescription(getDescription(parameterType));
            Schema<Object> schema = new Schema<>();
            schema.setEnum(getValues(parameterType));
            parameterModel.setSchema(schema);
        }
        return parameterModel;
    }
}
