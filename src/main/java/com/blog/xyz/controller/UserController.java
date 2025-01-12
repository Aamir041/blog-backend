package com.blog.xyz.controller;

import com.blog.xyz.dtos.UserResponse;
import com.blog.xyz.dtos.UserUpdateRequest;
import com.blog.xyz.dtos.Users;
import com.blog.xyz.dtos.UserRequest;
import com.blog.xyz.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {

    private UserService userService;
    private ObjectMapper objectMapper;

    @Autowired
    public UserController(UserService userService, ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
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

    @GetMapping("/{username}")
    public UserResponse getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping
    public UserResponse getUserByUid(@RequestParam Integer id){
        return userService.getUserByUid(id);
    }

    @PutMapping
    public UserResponse updateUser(@RequestBody UserUpdateRequest userUpdateRequest){
        UserResponse updatedUser = userService.updateUserRequest(userUpdateRequest);
        return updatedUser;
    }

    @DeleteMapping
    public void deleteUser(@RequestParam Integer id){
        userService.deleteUserById(id);
    }

}
