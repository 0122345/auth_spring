package com.yourpackage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.task.signingentilspring.model.User;
import com.task.signingentilspring.repository.UserRepository;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User saveUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    @Service
    public class UserServiceImpl implements UserService {

        @Autowired
        private UserRepository userRepository;

        @Override
        public List<User> getAllUsers() {
            return userRepository.findAll();
        }

        @Override
        public User getUserById(Long id) {
            return userRepository.findById(id)
                    .orElseThrow();
        }

        @Override
        public User updateUser(Long id, User user) {
            User existingUser = getUserById(id);
            // Update user fields
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setDateOfBirth(user.getDateOfBirth());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setRole(user.getRole());
            return userRepository.save(existingUser);
        }

        @Override
        public void deleteUser(Long id) {
            userRepository.deleteById(id);
        }

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public User saveUser(User user) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
    }

}