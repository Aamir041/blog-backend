package com.blog.xyz.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "postusermapping")
public class PostUserMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer uid;

    @Column(name = "postid")
    private Integer postId;

    @Column(name = "userid")
    private Integer userId;
}

