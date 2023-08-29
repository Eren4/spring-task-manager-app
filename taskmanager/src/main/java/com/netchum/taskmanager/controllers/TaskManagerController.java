package com.netchum.taskmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskManagerController {

    @GetMapping("/")
    public String getHomePage() {
        return "home-page";
    }
}
