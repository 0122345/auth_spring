package com.task.signingentilspring.service;

import com.task.signingentilspring.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User findByEmail(String email);
    void createPasswordResetTokenForUser(User user, String token);
    boolean validatePasswordResetToken(String token);
    void resetPassword(String token, String newPassword);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User saveUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User createUser(User user);
}