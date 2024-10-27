package com.yourpackage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.task.signingentilspring.model.User;
import com.task.signingentilspring.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String showIndex() {
        return "index";
    }
    
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/admin/users")
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users";
    }
    
    @GetMapping("/admin/users/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/user-form";
    }
    
    @PostMapping("/admin/users/create")
    public String createUser(@Valid @ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }
    
    @GetMapping("/admin/users/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/user-form";
    }
    
    @PostMapping("/admin/users/edit/{id}")
    public String updateUser(@PathVariable Long id, @Valid @ModelAttribute User user) {
        userService.updateUser(id, user);
        return "redirect:/admin/users";
    }
    
    @GetMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}