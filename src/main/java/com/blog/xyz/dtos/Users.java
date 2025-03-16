package com.blog.xyz.dtos;

import com.blog.xyz.annotation.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer uid;

    @Column(nullable = false)
    @NotNull    (message = "Username cannot be null")
    private String username;

    @Column(nullable = false)
    @NotNull(message = "Password cannot be null")
    private String password;

    @Column()
    private String bio;

    @Column()
    private Date birthdate;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
