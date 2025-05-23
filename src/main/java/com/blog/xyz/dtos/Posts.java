package com.blog.xyz.dtos;

import com.blog.xyz.annotation.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "posts")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "postid")
    private Integer postId;

    @Column(nullable = false)
    @NotNull(message = "Title cannot be null")
    private String title;

    @Column(nullable = false)
    @NotNull(message = "Contents of a blog can not be null")
    private String content;

    @Column()
    private String category;

    @Column(name = "publicationdate")
    private Date publicationDate;

    @Column()
    private String tags;

    @Column()
    private Integer authorid;
}

