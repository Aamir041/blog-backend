package com.blog.xyz;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Slf4j
public class JwtTest {
    @Test
    public void test(){
        String secret = "youKnowSecretyouKnowSecretyouKnowSecretyouKnowSecret";
        SecretKey secretKey = generalKey(secret);
        String token = Jwts.builder()
                .subject("Aamir")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(secretKey)
                .compact();

        System.out.println(token);
    }

    public static SecretKey generalKey(String secret){
        byte[] encodeKey = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(encodeKey);
    }

}
