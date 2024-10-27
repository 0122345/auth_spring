package com.company.project.service;

import java.util.List;
import java.util.Optional;
import com.company.project.model.User;

public interface UserService {
    User save(User user);
    User findByUsername(String username);
    User findById(Long id);
    User updateUser(User user);
    void delete(Long id);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    com.task.signingentilspring.model.User findByEmail(String email);
    void createPasswordResetTokenForUser(com.task.signingentilspring.model.User user, String token);
    boolean validatePasswordResetToken(String token);
    void resetPassword(String token, String password);
    User createUser(User user);
}
