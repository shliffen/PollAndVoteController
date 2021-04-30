package ua.com.foxminded.quickpoll.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
@EnableSwagger2
public class SpringFoxConfig {

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Quick Poll API",
                "QuickPoll Api for creating and managing polls.",
                "1.0",
                "Terms of service", new Contact("Ilya Makh", "www.some-url.com", "some@email.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

    @Bean
    public Docket swaggerPersonApi1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("QuickPollControllerApi-1.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage("ua.com.foxminded.quickpoll.v1.controller"))
                .paths(PathSelectors.ant("/v1/**"))
                .build().apiInfo(apiInfo()).useDefaultResponseMessages(false);
    }

    @Bean
    public Docket swaggerPersonApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("QuickPollControllerApi-2.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage("ua.com.foxminded.quickpoll.v2.controller"))
                .paths(PathSelectors.ant("/v2/**"))
                .build().apiInfo(apiInfo()).useDefaultResponseMessages(false);
    }

    @Bean
    public Docket swaggerPersonApi3() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("QuickPollControllerApi-3.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage("ua.com.foxminded.quickpoll.v3.controller"))
                .paths(PathSelectors.ant("/v3/**"))
                .build().apiInfo(apiInfo()).useDefaultResponseMessages(false);
    }

}
