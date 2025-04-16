package com.blog.xyz.service;

import com.blog.xyz.dtos.UserRequest;
import com.blog.xyz.dtos.UserResponse;
import com.blog.xyz.dtos.UserUpdateRequest;
import com.blog.xyz.dtos.Users;
import com.blog.xyz.dtos.UserDeleteResponse;

import java.util.List;

public interface UserService {
    UserResponse addUser(UserRequest userRequest);
    List<UserResponse> getAllUsers();
    UserResponse getUserByUsername(String username);
    UserResponse getUserByUid(Integer id);
    UserResponse updateUserRequest(UserUpdateRequest userUpdateRequest);
    void deleteUserById(Integer id);
    UserDeleteResponse deleteUserByUsername(String username);
}
