package com.blog.xyz.service;

import com.blog.xyz.dtos.PostRequest;
import com.blog.xyz.dtos.PostResponse;

public interface PostService {
    PostResponse createPost(PostRequest postRequest, String username);
}
