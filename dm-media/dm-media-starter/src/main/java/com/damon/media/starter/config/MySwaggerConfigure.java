package com.damon.media.starter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

/***
 * @author Damon S.
 */
@Configuration
@EnableSwagger2
public class MySwaggerConfigure {

    @Bean
    public Docket createMediaApi() {
        List<Parameter> parameters = new ArrayList<>();

        parameters.add(new ParameterBuilder()
                .name("token")
                .description("令牌")
                .required(true)
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(mediaApiInfo())
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.damon.media"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);
    }

    private ApiInfo mediaApiInfo() {
        return new ApiInfoBuilder()
                .title("DM联盟")
                .description("联盟资源管理")
                .version("v1")
                .build();
    }
}
