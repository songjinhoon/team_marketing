package com.devteam.marketing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private static final String TITLE = "Team Marketing Rest Api";
    private static final String VERSION = "0.0.1";
    private static final String DESCRIPTION = "Api 명세서";

    @Bean
    public Docket restAPI() {
/*        Parameter parameterBuilder = new ParameterBuilder()
                .name(HttpHeaders.AUTHORIZATION)
                .description("Access Tocken")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        List<Parameter> globalParamters = new ArrayList<>();
        globalParamters.add(parameterBuilder);*/

        return new Docket(DocumentationType.SWAGGER_2)
//                .globalOperationParameters(globalParamters)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.devteam.marketing.domain"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .version(VERSION)
                .description(DESCRIPTION)
                .build();
    }

}
