package com.blog.xyz.util;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;

public class PasswordUtil {
    @Value("${key}")
    public Integer key;

    public String hashPassword(String password){
        String hashPassword = BCrypt.withDefaults().hashToString(key,password.toCharArray());
        return hashPassword;
    }
}
