package com.example.project_box_backend.controllers;

import com.example.project_box_backend.dto.RegisterUserDto;
import com.example.project_box_backend.models.User;
import com.example.project_box_backend.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterUserDto registerCredentials) throws BadRequestException {
        try {
            userService.loadUserByUsername(registerCredentials.name());
            throw new BadRequestException("User with name: \"" + registerCredentials.name() + "\" is already present");
        } catch (Exception e) {
            var userToSave = new User();
            userToSave.setUsername(registerCredentials.name());
            userToSave.setPassword(registerCredentials.password());
            userService.saveUser(userToSave);
        }
    }
}
