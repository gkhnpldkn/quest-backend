package com.gokhan.dkdemo.controllers;

import com.gokhan.dkdemo.requests.UserRequest;
import com.gokhan.dkdemo.responses.UserResponse;
import com.gokhan.dkdemo.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserRequest loginRequest) {
        // TODO: Implement proper authentication with JWT
        UserResponse user = userService.getUserByUsername(loginRequest.getUserName());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return user;
        }
        throw new RuntimeException("Invalid username or password");
    }
} 