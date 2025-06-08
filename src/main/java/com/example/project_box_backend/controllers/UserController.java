package com.example.project_box_backend.controllers;

import com.example.project_box_backend.models.User;
import com.example.project_box_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user-profile")
    public UserDetails getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }
}
