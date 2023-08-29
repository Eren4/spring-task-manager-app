package com.netchum.taskmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskManagerController {

    @GetMapping("/")
    public String getHomePage() {
        return "home-page";
    }

    @PostMapping("/get-login")
    public String getLoginScreen() {
        return "login-page";
    }

    @PostMapping("/")
    public String getMainScreen() {
        return "home-page";
    }
}
