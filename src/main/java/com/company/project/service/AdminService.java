package com.company.project.service;

import jakarta.validation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.company.project.model.User;

 

@Controller
@RequestMapping("/admin")
public class AdminService {

  @Autowired
  private UserService userService;

  @GetMapping("/user/edit/{id}")
  public String editUser(@PathVariable Long id, Model model) {
    User user = userService.findById(id);
    model.addAttribute("user", user);
    return "edit-user";
  }

  @PostMapping("/user/update")
  public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
    if (result.hasErrors()) {
      return "edit-user";
    }
    userService.updateUser(user);
    return "redirect:/admin/users";
  }

  @GetMapping("/user/delete/{id}")
  public String deleteUser(@PathVariable Long id) {
    userService.delete(id);
    return "redirect:/admin/users";
  }
}


