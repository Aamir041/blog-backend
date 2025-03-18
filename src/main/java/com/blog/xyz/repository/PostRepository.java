package com.blog.xyz.repository;

import com.blog.xyz.dtos.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts, Integer> {
}
