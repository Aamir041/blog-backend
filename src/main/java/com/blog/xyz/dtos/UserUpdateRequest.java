package com.blog.xyz.dtos;

import com.blog.xyz.annotation.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserUpdateRequest {
    @NotNull(message = "Username cannot be null")
    private String username;
    private String bio;
    private Date birthdate;
}
