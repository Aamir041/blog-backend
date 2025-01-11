package com.blog.xyz.config;

import com.blog.xyz.util.PasswordUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public PasswordUtil passwordUtil(){
        return new PasswordUtil();
    }

}
