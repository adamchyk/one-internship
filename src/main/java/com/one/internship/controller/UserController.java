package com.one.internship.controller;

import com.one.internship.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> usersList = new ArrayList<>();
        usersList.add(new User());
        return usersList;
    }

}
