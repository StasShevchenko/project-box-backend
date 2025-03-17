package com.example.project_box_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration(proxyBeanMethods = false)
public class WebConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(final CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedOrigins(environment.getProperty("DEVELOPMENT_ALLOWED_HOST"))
                        .allowCredentials(true).allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}