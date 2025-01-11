package com.blog.xyz.service;

import com.blog.xyz.dtos.UserResponse;
import com.blog.xyz.dtos.Users;

import java.util.List;

public interface UserService {
    Users addUser(Users user);
    List<UserResponse> getAllUsers();
    
}
