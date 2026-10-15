package com.example.jwtvalidation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.jwtvalidation.dto.TokenRequest;
import com.example.jwtvalidation.service.JwtService;

@RestController
@RequestMapping("/api/jwt")
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/validate")
    public String validate(@RequestBody TokenRequest request) {
        return jwtService.validateToken(request.getToken());
    }

    @GetMapping("/generate")
    public String generate() {
        return jwtService.generateToken();
    }

}