package com.example.order.connections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantIdentifierConfig()).addPathPatterns("/order");
    }

    @Bean
    public TenantIdentifierConfig tenantIdentifierConfig(){
        return new TenantIdentifierConfig();
    }
}