package com.example.project_box_backend.dto;

import com.example.project_box_backend.annotations.ValidPassword;
import jakarta.validation.constraints.Email;

public record RegisterUserDto(@Email(message = "Неверный формат почты") String name, @ValidPassword String password) {
}
