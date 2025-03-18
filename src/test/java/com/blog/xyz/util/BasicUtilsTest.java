package com.blog.xyz.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public  class BasicUtilsTest {
    @Disabled
    @Test
    public void getCorrectUsernameFromToken() {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBbHZhaCIsImlhdCI6MTc0MjMxNjkwMiwiZXhwIjoxNzQyMzIwNTAyfQ.8e3RgbzzpdyjrAOoofq9UCE-kc6aElX1OprAqJiIYm4xh7jI3GKdauZ3dT4UmtOrlajWklNUq0xFV97Vft1Jtw");
        String username = BasicUtils.getUsername(mockHttpServletRequest);
        assertEquals(username,"Alvah");
    }
}