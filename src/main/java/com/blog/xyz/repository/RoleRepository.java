package com.blog.xyz.repository;

import com.blog.xyz.dtos.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
