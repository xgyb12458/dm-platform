package com.damon.media.starter.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.servlet.Filter;


//@Configuration
public class MvcConfigAdapter extends WebMvcConfigurerAdapter {

    @Autowired
    private RequestMappingHandlerAdapter adapter;

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    public Filter encodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor()).excludePathPatterns("/", "/mgmt/**", "/swagger**/**", "/v2/api-docs**", "/error",
                        "/v1/adx/**", "/v1/connector/**", "/v1/tencent/**", "/adx/**", "/v1/plans/status");
    }


//    @PostConstruct
//    public void initEditableValidation() {
//        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) adapter.getWebBindingInitializer();
//        if (!ObjectUtils.isEmpty(initializer.getConversionService())) {
//            GenericConversionService conversionService = (GenericConversionService) initializer.getConversionService();
//            conversionService.addConverter(new StringToDateConverter());
//            conversionService.addConverter(new StringToLocalDateConverter());
//            conversionService.addConverter(new StringToLocalDateTimeConverter());
//        }
//    }

}
