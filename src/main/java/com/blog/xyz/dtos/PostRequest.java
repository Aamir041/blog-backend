package com.blog.xyz.dtos;

import com.blog.xyz.annotation.NotNull;
import lombok.Data;

@Data
public class PostRequest {
    @NotNull(message = "Title can not be null")
    private String title;
    @NotNull (message = "Blogs content cannot be null")
    private String content;
    private String category;
    private String tags;
    private String author;
}
