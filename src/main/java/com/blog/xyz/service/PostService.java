package com.blog.xyz.service;

import com.blog.xyz.dtos.PostRequest;
import com.blog.xyz.dtos.PostResponse;
import com.blog.xyz.dtos.paginated_dtos.PaginatedPostResponse;

import java.util.List;

public interface PostService {
    PostResponse createPost(PostRequest postRequest, String username);
    PaginatedPostResponse getPosts(String username, Integer pageNumber);
}
