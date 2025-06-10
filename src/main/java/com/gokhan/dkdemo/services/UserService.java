package com.gokhan.dkdemo.services;

import com.gokhan.dkdemo.entity.User;
import com.gokhan.dkdemo.repos.UserRepository;
import com.gokhan.dkdemo.requests.UserRequest;
import com.gokhan.dkdemo.responses.UserResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAll() {
        List<User> userList = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();
        userList.forEach(user -> {
            UserResponse userResponse = new UserResponse(user.getId(), user.getUserName(), user.getPassword());
            userResponseList.add(userResponse);
        });
        return userResponseList;
    }

    public UserResponse saveOneUser(UserRequest newUser) {
        User user = new User();
        user.setUserName(newUser.getUserName());
        user.setPassword(newUser.getPassword());

        user = userRepository.save(user);

        return new UserResponse(user.getId(), user.getUserName(), user.getPassword());
    }

    public UserResponse getOneUser(Long userId) {
        User user = getUserById(userId);
        if (user == null) {
            return null;
        }
        return new UserResponse(user.getId(), user.getUserName(), user.getPassword());
    }

    public User getUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user;
    }

    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            return null;
        }
        return new UserResponse(user.getId(), user.getUserName(), user.getPassword());
    }

    public UserResponse updateOneUser(Long userId, UserRequest newUser) {
        User foundUser = getUserById(userId);
        if (foundUser != null) {
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return new UserResponse(foundUser.getId(), foundUser.getUserName(), foundUser.getPassword());
        } else {
            return null;
        }
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
