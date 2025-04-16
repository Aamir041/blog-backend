package com.blog.xyz.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@ToString
public class UserDeleteEvenDto extends ApplicationEvent {
    private String transactionId;
    private Integer authorId;

    public UserDeleteEvenDto(Object source, String transactionId, Integer authorId) {
        super(source);
        this.authorId = authorId;
        this.transactionId = transactionId;
    }
}
