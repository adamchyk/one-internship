package com.one.internship.controller;

import com.one.internship.entity.User;
import com.one.internship.model.UserInfo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    List<User> usersList = new ArrayList<>();

    @GetMapping("/users")
    public List<UserInfo> getUsers() {
        List<UserInfo> userInfos = new ArrayList<>();
        for (int i = 0; i < usersList.size(); i++){
            UserInfo userInfo = new UserInfo();
            userInfo.setId(usersList.get(i).getId());
            userInfo.setUsername(usersList.get(i).getUsername());
            userInfo.setAdmin(usersList.get(i).isIsAdmin());
            userInfos.add(userInfo);
        }
        return userInfos;
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user) {
        user.setId((Integer) (usersList.size() + 1));
        usersList.add(user);
    }

}
