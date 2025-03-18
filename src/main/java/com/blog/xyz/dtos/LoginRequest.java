package com.blog.xyz.dtos;

import com.blog.xyz.annotation.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotNull(message = "Username cannot be empty")
    private String username;
    @NotNull(message = "Password cannot be empty")
    private String password;
}
