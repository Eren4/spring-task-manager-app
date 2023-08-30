package com.netchum.taskmanager.controllers;

import com.netchum.taskmanager.entities.User;
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginToSystem(@RequestParam String email, @RequestParam String password,
                        Model model) {
        boolean isAuthenticated = userService.authenticateUser(email, password);

        if(isAuthenticated) {
            String username = userService.getUserByEmail(email).getUsername();
            username = makeFirstLetterCapital(username);
            model.addAttribute("username", username);

            return "task-list";
        }
        else {
            model.addAttribute("error", "Invalid email or password");
            return "login-page";
        }
    }

    @PostMapping("/signup")
    public String signupToSystem(@RequestParam String username, @RequestParam String email,
                                 @RequestParam String password, @RequestParam String passwordAgain,
                                 Model model) {
        if(userService.isEmailRegistered(email)) {
            model.addAttribute("email-error", "Email already used by another user");
            return "signup-page";
        }
        else if(userService.isUsernameRegistered(username)) {
            model.addAttribute("username-error", "Username not available");
            return "signup-page";
        }
        else if(!password.equals(passwordAgain)) {
            model.addAttribute("password-error", "Passwords don't match");
            return "signup-page";
        }

        User user = new User(username, email, password);

        userService.createUser(user);

        model.addAttribute("username", makeFirstLetterCapital(user.getUsername()));

        return "task-list";
    }

    private String makeFirstLetterCapital(String username) {
        return username.substring(0, 1).toUpperCase() + username.substring(1, username.length());
    }
}
