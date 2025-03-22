package com.blog.xyz.dtos;

import com.blog.xyz.annotation.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Integer postId;
    private String title;
    private String content;
    private String category;
    private Date publicationDate;
    private String tags;
    private String author;
}
