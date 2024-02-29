package cn.allbs.admin.config;

import cn.allbs.admin.config.converter.EnumConverterFactory;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 类 WebmvcConfig 添加自定义枚举转换配置
 *
 * @author ChenQi
 * @date 2024/2/29
 */
@AllArgsConstructor
@Configuration(proxyBeanMethods = false)
public class WebmvcConfig implements WebMvcConfigurer {

    private final EnumConverterFactory<?, ?> enumConverterFactory;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(enumConverterFactory);
    }
}
