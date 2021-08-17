package com.one.internship.controller;

import com.one.internship.entity.User;
import com.one.internship.model.ChangePasswordRequest;
import com.one.internship.model.UserInfo;
import com.one.internship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public List<UserInfo> getUsers() {
        List<UserInfo> userInfos = new ArrayList<>();
        List<User> usersList = userRepository.findAll();
        for (int i = 0; i < usersList.size(); i++) {
            UserInfo userInfo = new UserInfo(usersList.get(i));
            userInfos.add(userInfo);
        }
        return userInfos;
    }

    @GetMapping("/me")
    public UserInfo getUser(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return new UserInfo(user);
    }

    @PostMapping("/users/password")
    public void changePassword(@RequestBody ChangePasswordRequest req) {
        User user = userRepository.findById(req.getUserId()).get();
        String encodedPassword = passwordEncoder.encode(req.getNewPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @PostMapping("/users/{userId}/enableDisable")
    public void accountEnable(@PathVariable Integer userId) {
        User user = userRepository.findById(userId).get();
        user.setAccountEnabled(!user.isAccountEnabled());
        userRepository.save(user);

    }

}
