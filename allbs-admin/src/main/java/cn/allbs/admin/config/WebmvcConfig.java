package cn.allbs.admin.config;

import cn.allbs.admin.config.constant.CommonConstants;
import cn.allbs.admin.config.constant.DatePattern;
import cn.allbs.admin.config.converter.EnumConverterFactory;
import cn.allbs.admin.config.time.JavaTimeParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 类 WebmvcConfig 添加自定义枚举转换配置
 *
 * @author ChenQi
 * @date 2024/2/29
 */
@AllArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class WebmvcConfig implements WebMvcConfigurer {

    private final EnumConverterFactory<?, ?> enumConverterFactory;

    @Bean
    @ConditionalOnMissingBean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            builder.locale(Locale.CHINA);
            builder.timeZone(TimeZone.getTimeZone(CommonConstants.ASIA_SHANGHAI));
            builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
//            builder.serializerByType(Long.class, ToStringSerializer.instance);
            builder.modules(new JavaTimeParser());
        };
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setTimeFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN));
        registrar.setDateFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN));
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN));
        registry.addConverterFactory(enumConverterFactory);
        registrar.registerFormatters(registry);
    }
}
