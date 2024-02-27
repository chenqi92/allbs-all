package cn.allbs.admin.config.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

/**
 * 类 Swagger3Config
 *
 * @author ChenQi
 * @since 2022/8/9 13:36
 */
@Configuration
@EnableConfigurationProperties({SpringDocProperties.class})
public class SpringDocConfig {

    private final SpringDocProperties springDocProperties;

    /**
     * 配置参数
     *
     * @param springDocProperties 配置参数
     */
    public SpringDocConfig(SpringDocProperties springDocProperties) {
        this.springDocProperties = springDocProperties;
    }

    /**
     * 配置openApi
     *
     * @param buildProperties
     * @return
     */
    @Bean
    @Profile({"dev", "test"})
    public OpenAPI openApi(ObjectProvider<BuildProperties> buildProperties) {
        OpenAPI openApi = new OpenAPI();

        if (null != springDocProperties.getSecuritySchemes() && !springDocProperties.getSecuritySchemes().isEmpty()) {
            //添加header
            openApi.components(new Components().securitySchemes(springDocProperties.getSecuritySchemes()));
            springDocProperties.getSecuritySchemes().keySet().forEach(key -> openApi.addSecurityItem(new SecurityRequirement().addList(key)));
        }

        // 基本信息
        openApi.info(new Info().title(springDocProperties.getTitle())
                .description(springDocProperties.getDescription())
                .version(Optional.ofNullable(buildProperties.getIfAvailable()).map(BuildProperties::getVersion).orElse(springDocProperties.getVersion())));
        if (!(springDocProperties.getServers() == null || springDocProperties.getServers().isEmpty())) {
            openApi.servers(springDocProperties.getServers());
        }

        // 使用knife4j的ui
        openApi.externalDocs(new ExternalDocumentation());
        return openApi;
    }

    @Bean
    @Profile({"dev", "test"})
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi
                .builder()
                .group(springDocProperties.getTitle()).pathsToMatch("/**").build();
    }
}
