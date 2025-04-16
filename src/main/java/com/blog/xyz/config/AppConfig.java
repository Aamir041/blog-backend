package com.blog.xyz.config;

import com.blog.xyz.util.PasswordUtil;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper()
                .configure(
                        JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true
                );
    }

    @Bean
    public PasswordUtil passwordUtil(){
        return new PasswordUtil();
    }

}
