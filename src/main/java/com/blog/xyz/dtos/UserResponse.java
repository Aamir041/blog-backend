package com.blog.xyz.dtos;

import com.blog.xyz.annotation.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
public class UserResponse {
    private Integer uid;
    private String username;
    private String bio;
    private Date birthdate;
}
