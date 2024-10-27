package com.company.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.project.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
}
