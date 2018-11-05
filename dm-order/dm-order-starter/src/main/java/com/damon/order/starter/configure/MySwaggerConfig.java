package com.damon.order.starter.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Damon S.
 */
@Configuration
@EnableSwagger2
public class MySwaggerConfig {

    @Bean
    public Docket createAppApi() {
        List<Parameter> parameters = new ArrayList<>();

        parameters.add(new ParameterBuilder()
                .name("token")
                .description("令牌")
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                .required(false)
                .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.damon.oauth"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("数据功能接口")
                .description("REST-API，所有接口以JSON格式返回")
                .version("v1")
                .termsOfServiceUrl("NO Terms of Service")
                .contact(new Contact("Damon S.", "http://www.damon.com", "songdeming@damon.cn"))
                .build();
    }
}
