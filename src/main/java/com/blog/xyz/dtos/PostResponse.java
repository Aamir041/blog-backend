package com.blog.xyz.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostResponse {
    private Integer postId;
    private String title;
    private String content;
    private String category;
    private Date publicationDate;
    private String tags;
    private String author;
}
