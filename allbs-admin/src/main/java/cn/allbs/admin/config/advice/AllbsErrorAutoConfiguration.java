package cn.allbs.admin.config.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Servlet;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 类 AllbsErrorAutoConfiguration
 *
 * @author ChenQi
 * @date 2024/4/1
 */
@AutoConfiguration(before = ErrorMvcAutoConfiguration.class)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class AllbsErrorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(value = ErrorAttributes.class, search = SearchStrategy.CURRENT)
    public DefaultErrorAttributes errorAttributes() {
        return new AllbsErrorAttributes();
    }

    @Bean
    @ConditionalOnMissingBean(value = ErrorController.class, search = SearchStrategy.CURRENT)
    public BasicErrorController basicErrorController(ObjectMapper objectMapper,
                                                     ErrorAttributes errorAttributes,
                                                     ServerProperties serverProperties) {
        return new AllbsErrorController(objectMapper, errorAttributes, serverProperties.getError());
    }
}
