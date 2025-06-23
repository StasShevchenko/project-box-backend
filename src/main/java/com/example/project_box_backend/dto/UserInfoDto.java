package com.example.project_box_backend.dto;

import com.example.project_box_backend.models.Role;
import com.example.project_box_backend.models.User;

import java.util.Set;

public class UserInfoDto {
    public Long id;
    public String login;
    public Set<Role> roles;

    public UserInfoDto(User user) {
        this.id = user.getId();
        this.login = user.getUsername();
        this.roles = user.getRoles();
    }
}
