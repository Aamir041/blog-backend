package com.blog.xyz.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class CustomSpringEventDto extends ApplicationEvent {
    private String message;

    public CustomSpringEventDto(Object source, String message) {
        super(source);
        this.message = message;
    }
}
