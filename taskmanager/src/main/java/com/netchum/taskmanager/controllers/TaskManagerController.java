package com.netchum.taskmanager.controllers;

import com.netchum.taskmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskManagerController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getHomePage() {
        return "home-page";
    }

    @PostMapping("/login-page")
    public String getLoginScreen() {
        return "login-page";
    }

    @PostMapping("/signup-page")
    public String getSignupScreen() {
        return "signup-page";
    }

    @PostMapping("/")
    public String getMainScreen() {
        return "home-page";
    }

    @RequestMapping(value = "/login-page", method = RequestMethod.POST)
    public String login(@RequestParam String email, @RequestParam String password,
                        Model model) {
        boolean isAuthenticated = userService.authenticateUser(email, password);

        if(isAuthenticated) {
            return "task-list";
        }
        else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }
}
