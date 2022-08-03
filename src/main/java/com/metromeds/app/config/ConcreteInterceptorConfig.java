package com.metromeds.app.config;

import com.metromeds.app.interceptor.Concrete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class ConcreteInterceptorConfig extends WebMvcConfigurerAdapter {
    @Autowired
    public Concrete concrete;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(concrete).addPathPatterns("/api/*");
    }
}