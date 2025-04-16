package com.blog.xyz.publisher;

import com.blog.xyz.dtos.UserDeleteEvenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserDeleteEventPublisher {
    @Autowired
    private ApplicationEventPublisher publisher;

    public void publishDeleteEvent(String transactionId, Integer authorId){
        UserDeleteEvenDto userDeleteEvenDto = new UserDeleteEvenDto(this, transactionId, authorId);
        log.info("Publishing event {}",userDeleteEvenDto);
        publisher.publishEvent(userDeleteEvenDto);
    }
}
