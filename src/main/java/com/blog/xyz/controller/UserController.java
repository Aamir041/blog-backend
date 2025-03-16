package com.blog.xyz.controller;

import com.blog.xyz.annotation.RequiredRole;
import com.blog.xyz.dtos.UserResponse;
import com.blog.xyz.dtos.UserUpdateRequest;
import com.blog.xyz.dtos.Users;
import com.blog.xyz.dtos.UserRequest;
import com.blog.xyz.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserResponse> getAllUser(){
        List<UserResponse> allUsers = null;
        try{
            log.info("Getting all the users");
            allUsers = userService.getAllUsers();
            log.info("Fetched all the users successfully");
        }
        catch (Exception exception){
            log.error("Error while getting all the users: {}", exception.getMessage());
        }
        return allUsers;
    }

    @GetMapping("/{uid}")
    public UserResponse getUserByUid(@PathVariable Integer uid){
        return userService.getUserByUid(uid);
    }

    @RequiredRole("ROLE_ADMIN")
    @PutMapping
    public UserResponse updateUser(@RequestBody UserUpdateRequest userUpdateRequest){
        UserResponse updatedUser = userService.updateUserRequest(userUpdateRequest);
        return updatedUser;
    }

    @RequiredRole("ROLE_ADMIN")
    @DeleteMapping
    public void deleteUser(@RequestParam Integer id){
        userService.deleteUserById(id);
    }

}

