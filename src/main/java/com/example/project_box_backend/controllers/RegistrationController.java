package com.example.project_box_backend.controllers;

import com.example.project_box_backend.dto.RegisterUserDto;
import com.example.project_box_backend.models.User;
import com.example.project_box_backend.service.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RestController
public class RegistrationController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterUserDto registerCredentials) throws BadRequestException {
        UserDetails existingUser = null;

        try {
            existingUser = userService.loadUserByUsername(registerCredentials.name());
        } catch (Exception _) {
        }

        if (existingUser != null) {
            throw new BadRequestException("User with name: \"" + registerCredentials.name() + "\" is already present");
        }

        var userToSave = new User();
        userToSave.setUsername(registerCredentials.name());
        userToSave.setPassword(registerCredentials.password());
        userService.saveUser(userToSave);
        return ResponseEntity.ok().build();
    }
}