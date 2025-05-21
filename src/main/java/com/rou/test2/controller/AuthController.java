package com.rou.test2.controller;

import com.rou.test2.model.User;
import com.rou.test2.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String authenticate(String username, String password, HttpSession session) {
        User user = userRepository.findByUsername(username);
        if(user != null && password.equals(user.getPassword())) {

            session.setAttribute("user", user);

            if(user.getRole().equals("ADMIN")) {
                return "redirect:/admin";
            } else if (user.getRole().equals("TEACHER")) {
                return "redirect:/teacher";
            } else if (user.getRole().equals("LIBRARIAN")) {
                return "redirect:/librarian";
            } else if (user.getRole().equals("MANAGER")) {
                return "redirect:/manager";
            } else {
                return "redirect:/student";
            }
        }
        else{
            return "login";
        }
    }
    @GetMapping("/teacher")
    public String teacher() {
        return "teacher";
    }
    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }
    @GetMapping("/student")
    public String student() {
        return "student";
    }
    @GetMapping("/librarian")
    public String librarian() {
        return "librarian";
    }
}
