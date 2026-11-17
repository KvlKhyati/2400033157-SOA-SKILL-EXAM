package com.example.jwtvalidation.service;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public String validateToken(String token) {

        try {

            Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return "Token Valid\nUsername : "
                    + claims.getSubject()
                    + "\nRole : "
                    + claims.get("role");

        }

        catch (ExpiredJwtException e) {
            return "Token Expired";
        }

        catch (Exception e) {
            return "Invalid Token";
        }

    }
    public String generateToken() {

        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject("Khyati KVL,2400033157")
                .claim("role", "ADMIN")
                .signWith(key)
                .compact();
    }

}