package com.blog.xyz.controller;

import com.blog.xyz.dtos.PostRequest;
import com.blog.xyz.dtos.PostResponse;
import com.blog.xyz.service.PostService;
import com.blog.xyz.util.BasicUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("/post")
public class PostController {

    PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
                        .getRequestAttributes()).getRequest();
        String username = BasicUtils.getUsername(request);
        PostResponse response = postService.createPost(postRequest, username);
        ResponseEntity<PostResponse> createdPost = ResponseEntity.ok(response);
        return createdPost;
    }

}
