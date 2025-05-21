package com.rou.test2.controller;

import com.rou.test2.model.School;
import com.rou.test2.model.User;
import com.rou.test2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String admin() {
        return "admin";
    }

    @GetMapping ("/users")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/new")
    public String createNewUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("schools", School.values());
        return "create_user";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            model.addAttribute("schools", School.values());
            return "edit_user";
        }
        else {
            return "redirect:/admin/users";
        }
    }
    @PostMapping("/users/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user) {
        Optional<User> existingUser= userRepository.findById(id);
        if (existingUser.isPresent()) {
            User updateUser = existingUser.get();
            updateUser.setUsername(user.getUsername());
            updateUser.setPassword(user.getPassword());
            updateUser.setRole(user.getRole());
            updateUser.setSchool(user.getSchool());
            userRepository.save(updateUser);
        }
        return "redirect:/admin/users";
    }
}
