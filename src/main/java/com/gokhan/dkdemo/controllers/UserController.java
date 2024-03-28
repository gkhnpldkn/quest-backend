package com.gokhan.dkdemo.controllers;

import com.gokhan.dkdemo.entity.User;

import com.gokhan.dkdemo.requests.UserRequest;
import com.gokhan.dkdemo.responses.UserResponse;
import com.gokhan.dkdemo.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAllUser() {
        return userService.getAll();
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest newUser) {
        return userService.saveOneUser(newUser);
    }

    @GetMapping("/{userId}")
    public UserResponse getOneUser(@PathVariable Long userId) {
        return userService.getOneUser(userId);
    }

    @PutMapping("/{userId}")                   // güncelleme yaptık
    public UserResponse updateOneUser(@PathVariable Long userId, @RequestBody UserRequest newUser) {
        return userService.updateOneUser(userId, newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(Long userId) {
        userService.deleteById(userId);
    }
}
