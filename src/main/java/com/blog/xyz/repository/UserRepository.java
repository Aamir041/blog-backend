package com.blog.xyz.repository;

import com.blog.xyz.dtos.UserResponse;
import com.blog.xyz.dtos.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findUserByUid(Integer uid);

    Users findByUsername(String username);
}
