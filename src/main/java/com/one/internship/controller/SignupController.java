package com.one.internship.controller;

import com.one.internship.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.management.relation.Role;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

public class SignupController {

    // userRepository
    // encoder

//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
//        if (userRepository.existByUsername(signupRequest.getUsername())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Username is already taken!"));
//        }
//
//        if (userRepository.existByEmail(signupRequest.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already in use!"));
//        }
//        User user = new User();
//        user.setUsername(signupRequest.getUsername());
//        user.setUsername(encoder.encode(signupRequest.getPassword()));
//        user.setIsAdmin(false);
//        userRepository.save(user);
//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//    }
}
