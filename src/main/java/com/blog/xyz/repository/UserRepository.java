package com.blog.xyz.repository;

import com.blog.xyz.dtos.UserResponse;
import com.blog.xyz.dtos.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query("SELECT new com.blog.xyz.dtos.UserResponse(u.uid, u.username, u.bio, u.birthdate) FROM Users AS u")
    List<UserResponse> findAllusers();

    @Query("SELECT new com.blog.xyz.dtos.UserResponse(u.uid, u.username, u.bio, u.birthdate) FROM Users AS u WHERE u.username = :username")
    UserResponse findUserByUsername(String username);

    @Query("SELECT new com.blog.xyz.dtos.UserResponse(u.uid, u.username, u.bio, u.birthdate) FROM Users AS u WHERE u.uid = :uid")
    UserResponse findUserByUid(Integer uid);
}
