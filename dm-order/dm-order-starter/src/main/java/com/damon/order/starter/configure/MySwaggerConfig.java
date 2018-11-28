package com.damon.order.starter.configure;

import com.damon.order.shared.constant.ApiConstants;
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

//@Configuration
//@EnableSwagger2
/**
 * SWAGGER接口页面配置
 * @author Damon S.
 */
public class MySwaggerConfig {
    private final static String BASE_PACKAGE = "com.damon.order";

    @Bean
    public Docket createAppApi() {
        List<Parameter> parameters = new ArrayList<>();

        parameters.add(new ParameterBuilder()
                .name("accessToken")
                .description("令牌")
                .required(false)
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("订单管理接口")
                .description("REST-API，所有接口以JSON格式返回")
                .version(ApiConstants.apiVersion())
                .build();
    }
}
