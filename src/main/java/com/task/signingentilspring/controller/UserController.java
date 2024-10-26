package com.task.signingentilspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.task.signingentilspring.model.User;
import com.task.signingentilspring.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }
    
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @Valid @ModelAttribute User user) {
        user.setId(id);  // Set the ID before updating
        userService.updateUser(user);
        return "redirect:/admin/users";
    }
    
    // Other methods remain the same
}
