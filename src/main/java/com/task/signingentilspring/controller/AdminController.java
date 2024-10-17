package com.task.signingentilspring.controller;
import com.task.signingentilspring.model.User;
import com.task.signingentilspring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users";
    }

    @GetMapping("/users/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/create-user";
    }

    @PostMapping("/users/create")
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            return "admin/edit-user";
        } else {
            redirectAttributes.addFlashAttribute("error", "User not found");
            return "redirect:/admin/users";
        }
    }

    @PostMapping("/users/edit")
    public String editUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        User updatedUser = userService.updateUser(user);
        if (updatedUser != null) {
            redirectAttributes.addFlashAttribute("message", "User updated successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to update user");
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("message", "User deleted successfully");
        return "redirect:/admin/users";
    }
}
