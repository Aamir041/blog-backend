package com.blog.xyz.controller;

import com.blog.xyz.annotation.RequiredRole;
import com.blog.xyz.dtos.UserResponse;
import com.blog.xyz.dtos.UserUpdateRequest;
import com.blog.xyz.dtos.UserDeleteResponse;
import com.blog.xyz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @DeleteMapping("/delete/{username}")
    public UserDeleteResponse deleteUserByUsername(@PathVariable String username){

        return userService.deleteUserByUsername(username);
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

