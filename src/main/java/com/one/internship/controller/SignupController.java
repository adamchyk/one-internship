package com.one.internship.controller;

import com.one.internship.entity.User;
import com.one.internship.model.LoginRequest;
import com.one.internship.model.MessageResponse;
import com.one.internship.model.SignupRequest;
import com.one.internship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SignupController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(encoder.encode(signupRequest.getPassword()));
        user.setIsAdmin(false);
        user.setAccountEnabled(true);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        if (!userRepository.existsByUsername(loginRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is not valid!"));
        }
        return ResponseEntity.ok(new MessageResponse("ok"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {

        return ResponseEntity.ok(new MessageResponse("ok"));
    }
}
