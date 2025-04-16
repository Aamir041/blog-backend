package com.blog.xyz.controller;

import com.blog.xyz.publisher.CustomEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
public class TempController {

    @Autowired
    private CustomEventPublisher customEventPublisher;

    @GetMapping("/event")
    public void publishCustomEvent(@RequestParam String message){
        customEventPublisher.publishCustomEvent(message);
    }
}
