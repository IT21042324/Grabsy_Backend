package com.grabsy.GrabsyBackend.config;

import com.grabsy.GrabsyBackend.Middleware.interceptor.RequestLoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new RequestLoggingInterceptor()).addPathPatterns("/api/**");
    }
}
