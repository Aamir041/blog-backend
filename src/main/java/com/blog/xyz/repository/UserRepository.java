package com.blog.xyz.repository;

import com.blog.xyz.dtos.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findUserByUid(Integer uid);

    @Query("select u from Users u where lower(u.username) = lower(:username)")
    Users findByUsername(@Param("username") String username);
}
