package me.zrxjava.common.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.List;

/**
 * @author void
 * @create 2020-09-16
 */

@Configuration
public class Knife4jConfig {


    public ApiKey apiKey() {
        return new ApiKey("BearerToken", "Authorization", "header");
    }


    public SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }


    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("BearerToken", authorizationScopes));
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("shell后台API文档")
                .description("restful接口")
                .contact(new Contact("zrxjava@163.com",null,null))
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
