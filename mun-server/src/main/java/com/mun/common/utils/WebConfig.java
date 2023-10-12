package com.mun.common.utils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration(value = "webInterceptorConfig")
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器，并指定拦截的路径
        registry.addInterceptor(new AllowOriginIntercepter()).addPathPatterns("/**");
    }
}

