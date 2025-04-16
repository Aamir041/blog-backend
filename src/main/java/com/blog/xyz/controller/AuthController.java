package com.blog.xyz.controller;

import com.blog.xyz.dtos.LoginRequest;
import com.blog.xyz.dtos.UserRequest;
import com.blog.xyz.dtos.UserResponse;
import com.blog.xyz.dtos.Users;
import com.blog.xyz.repository.UserRepository;
import com.blog.xyz.service.UserService;
import com.blog.xyz.util.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    private AuthenticationManager authenticationManager;

    private JwtUtils jwtUtils;

    @Autowired
    public  AuthController(UserService userService, AuthenticationManager authenticationManager, JwtUtils jwtUtils){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;

    }

    @PostMapping("/signup")
    public UserResponse addUsers(@RequestBody @Valid UserRequest userRequest){
        UserResponse user = userService.addUser(userRequest);
        return user;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.generateToken(userDetails.getUsername());
    }



}
