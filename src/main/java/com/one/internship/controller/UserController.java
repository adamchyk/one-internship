package com.one.internship.controller;

import com.one.internship.entity.User;
import com.one.internship.model.UserInfo;
import com.one.internship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<UserInfo> getUsers() {
        List<UserInfo> userInfos = new ArrayList<>();
        List<User> usersList = userRepository.findAll();
        for (int i = 0; i < usersList.size(); i++){
            UserInfo userInfo = new UserInfo();
            userInfo.setId(usersList.get(i).getId());
            userInfo.setUsername(usersList.get(i).getUsername());
            userInfo.setAdmin(usersList.get(i).isIsAdmin());
            userInfo.setEnabled(usersList.get(i).isAccountEnabled());
            userInfos.add(userInfo);
        }
        return userInfos;
    }

    @PostMapping("/users/{id}/enable")
    public void enableUser(@RequestBody User user) {

    }

    @PostMapping("/users/{id}/disable")
    public void disableUser(@RequestBody User user) {

    }
}
