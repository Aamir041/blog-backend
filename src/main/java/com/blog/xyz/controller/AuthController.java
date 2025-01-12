package com.blog.xyz.controller;

import com.blog.xyz.dtos.UserRequest;
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

@RequestMapping("/auth")
@RestController
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
    public void addUsers(@RequestBody @Valid UserRequest userRequest){
        Users savedUser = userService.addUser(userRequest);
    }

    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.generateToken(userDetails.getUsername());
    }



}
