package com.blog.xyz.publisher;

import com.blog.xyz.dtos.CustomSpringEventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomEventPublisher {
    @Autowired
    private ApplicationEventPublisher publisher;

    public void publishCustomEvent(String message){
        CustomSpringEventDto customSpringEventDto = new CustomSpringEventDto(this,message);
        log.info("Publishing custom spring event with message: {}", message);
        publisher.publishEvent(customSpringEventDto);
    }
}
