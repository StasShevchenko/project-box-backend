package com.example.project_box_backend.repository;

import com.example.project_box_backend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}