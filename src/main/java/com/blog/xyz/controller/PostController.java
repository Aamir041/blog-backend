package com.blog.xyz.controller;

import com.blog.xyz.dtos.PostRequest;
import com.blog.xyz.dtos.PostResponse;
import com.blog.xyz.dtos.paginated_dtos.PaginatedPostResponse;
import com.blog.xyz.service.PostService;
import com.blog.xyz.util.BasicUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        if(!username.equalsIgnoreCase(postRequest.getAuthor())){
            throw new SecurityException("Not Allowed to publish under different author");
        }
        PostResponse response = postService.createPost(postRequest, username);
        ResponseEntity<PostResponse> createdPost = ResponseEntity.ok(response);
        return createdPost;
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<PaginatedPostResponse> getPosts(@PathVariable String username ,@RequestParam Integer pageNumber){
        if(pageNumber == null){
            pageNumber = 0;
        }
        PaginatedPostResponse response =  postService.getPosts(username, pageNumber);
        return ResponseEntity.ok(response);
    }

}
