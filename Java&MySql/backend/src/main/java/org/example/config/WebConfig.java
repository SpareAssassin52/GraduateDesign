package org.example.config;

import org.example.interceptors.AdminInterceptor;
import org.example.interceptors.ExpertInterceptor;
import org.example.interceptors.Logininterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class WebConfig implements WebMvcConfigurer {    //添加interceptor

    @Autowired
    private Logininterceptor logininterceptor;

    @Autowired
    private ExpertInterceptor expertInterceptor;

    @Autowired
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //login and register request should not intercept
        registry.addInterceptor(logininterceptor)
                .excludePathPatterns("/user/login", "/user/register", "/user/updateRole")
                .excludePathPatterns("/review/**")
                .excludePathPatterns("/roleChange/change")
                //.excludePathPatterns("/adminActions")
                .addPathPatterns("/**")
                .addPathPatterns("/review/findByTopic", "/review/delete");

        registry.addInterceptor(expertInterceptor)
                .addPathPatterns("/review/**")
                .excludePathPatterns("/review/findByTopic", "/review/delete");

        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/roleChange/change")
                .addPathPatterns("/user/updateRole");
    }
}
