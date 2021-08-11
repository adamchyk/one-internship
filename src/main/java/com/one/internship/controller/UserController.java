package com.one.internship.controller;

import com.one.internship.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class UserController {

    List<User> usersList = new ArrayList<>();

    @GetMapping("/users")
    public List<User> getUsers() {
        return usersList;
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user) {
        user.setId((long) (usersList.size() + 1));
        usersList.add(user);
    }

}
