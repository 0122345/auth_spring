package com.yourpackage.service;

import com.company.project.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);   
    User saveUser(User user);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User findByEmail(String email);
    void createPasswordResetTokenForUser(User user, String token);
    boolean validatePasswordResetToken(String token);
    void resetPassword(String token, String newPassword);
}