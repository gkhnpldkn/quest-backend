package com.gokhan.dkdemo.services;

import com.gokhan.dkdemo.entity.User;
import com.gokhan.dkdemo.repos.UserRepository;
import com.gokhan.dkdemo.requests.UserRequest;

import com.gokhan.dkdemo.responses.UserResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            UserResponse userResponse = UserResponse.builder()
                    .id(user.getId())
                    .userName(user.getUserName())
                    .password(user.getPassword())
                    .build();
            userResponseList.add(userResponse);
        });
        return userResponseList;
    }

    public UserResponse saveOneUser(UserRequest newUser) {
        User user = User.builder().userName(newUser.getUserName()).password(newUser.getPassword()).build();

        user = userRepository.save(user);

        return UserResponse.builder().id(user.getId()).userName(user.getUserName()).password(user.getPassword()).build();
    }

    public UserResponse getOneUser(Long userId) {
        User user = getUserById(userId);
        if (user == null) {
            return null;
        }
        UserResponse userResponse = UserResponse.builder().id(user.getId()).userName(user.getUserName()).password(user.getPassword()).build();
        return userResponse;
    }

    public User getUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user;
    }

    public UserResponse updateOneUser(Long userId, UserRequest newUser) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User foundUser = userOptional.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return UserResponse.builder().id(foundUser.getId()).userName(foundUser.getUserName()).password(foundUser.getPassword()).build();
        } else {
            return null;
        }

    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

}
