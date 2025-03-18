package com.blog.xyz.util;

import jakarta.servlet.http.HttpServletRequest;

public class BasicUtils {

    private static final String AUTHORIZATION = "Authorization";

    public static String getUsername(HttpServletRequest request){
        String token = request.getHeader(AUTHORIZATION).substring(7);
        String username = JwtUtils.getUsernameFromToken(token);
        return username;
    }
}
