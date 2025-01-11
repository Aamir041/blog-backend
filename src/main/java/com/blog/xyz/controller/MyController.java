package com.blog.xyz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MyController {

    @GetMapping("/health")
    public Map health(){
        Map<String,String> health = new HashMap<>();
        health.put("status", "up");
        return health;
    }

}
