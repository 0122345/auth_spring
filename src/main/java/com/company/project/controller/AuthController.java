package com.company.project.controller;

import java.security.Principal;
import jakarta.validation.Valid;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.security.access.prepost.PreAuthorize;
 

import com.company.project.model.User;
import com.company.project.service.UserService;

@Controller
public class AuthController {
    private final UserService userService;

    // @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
      @PostMapping("/signup")
      public String signup(@Valid @ModelAttribute("user") User user, BindingResult result) {
          if (result.hasErrors()) {
              return "signup";
          }
          userService.createUser(user);
          return "redirect:/login";
      }
    
      @GetMapping("/profile")
      public String userProfile(Principal principal, Model model) {
          User user = userService.findByUsername(principal.getName());
          model.addAttribute("user", user);
          return "profile";
      }

      @PreAuthorize("hasRole('ADMIN')")
      @GetMapping("/user/edit/{id}")
      public String editUser(@PathVariable Long id, Model model) {
          User user = userService.findById(id);
          model.addAttribute("user", user);
          return "edit-user";
      }

      @PreAuthorize("hasRole('ADMIN')")
      @PostMapping("/user/update")
      public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
          if (result.hasErrors()) {
              return "edit-user";
          }
          userService.update(user);
          return "redirect:/admin/users";
      }

      @PreAuthorize("hasRole('ADMIN')")
      @GetMapping("/user/delete/{id}")
      public String deleteUser(@PathVariable Long id) {
          userService.delete(id);
          return "redirect:/admin/users";
      }
    


@GetMapping("/admin/users")
public String listUsers(Model model) {
    List<User> users = userService.findAll();
    model.addAttribute("users", users);
    return "user-list";
}
}
