package com.blog.xyz.service;

import com.blog.xyz.dtos.UserRequest;
import com.blog.xyz.dtos.UserResponse;
import com.blog.xyz.dtos.UserUpdateRequest;
import com.blog.xyz.dtos.Users;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {
    Users addUser(UserRequest userRequest);
    List<UserResponse> getAllUsers();
    UserResponse getUserByUsername(String username);
    UserResponse getUserByUid(Integer id);
    UserResponse updateUserRequest(UserUpdateRequest userUpdateRequest);
    void deleteUserById(Integer id);
}
