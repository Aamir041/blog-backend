package com.blog.xyz.listener;

import com.blog.xyz.dtos.CustomSpringEventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEventDto> {
    @Override
    public void onApplicationEvent(CustomSpringEventDto event) {
        log.info("Received custom spring event with message : {}", event.getMessage());
    }
}
