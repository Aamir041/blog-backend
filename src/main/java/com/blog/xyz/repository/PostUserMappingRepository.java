package com.blog.xyz.repository;

import com.blog.xyz.dtos.PostUserMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostUserMappingRepository extends JpaRepository<PostUserMapping, Integer> {
}
