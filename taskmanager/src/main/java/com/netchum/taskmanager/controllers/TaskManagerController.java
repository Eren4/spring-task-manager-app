package com.netchum.taskmanager.controllers;

import com.netchum.taskmanager.entities.Task;
import com.netchum.taskmanager.entities.User;
import com.netchum.taskmanager.services.TaskService;
import com.netchum.taskmanager.services.UserService;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class TaskManagerController {

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

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

    @PostMapping("/task-screen-login")
    public String loginToSystem(@RequestParam String email,
                                @RequestParam String password,
                                Model model) {
        boolean isAuthenticated = userService.authenticateUser(email, password);

        if(isAuthenticated) {
            String username = userService.getUserByEmail(email).getUsername();
            username = makeFirstLetterCapital(username);

            int userId = userService.getUserByEmail(email).getId();
            List<Task> tasks = taskService.getTasksByUserId(userId);

            model.addAttribute("username", username);
            model.addAttribute("tasks", tasks);
            model.addAttribute("userId", userId);

            return "task-list";
        }
        else {
            model.addAttribute("error", "Invalid email or password");
            return "login-page";
        }
    }

    @PostMapping("/task-screen-signup")
    public String signupToSystem(@RequestParam String username, @RequestParam String email,
                                 @RequestParam String password, @RequestParam String passwordAgain,
                                 Model model) {
        String emailRegex = "^[^@]+@[^@]+\\.[a-zA-Z]{3}$";

        if(userService.isEmailRegistered(email)) {
            model.addAttribute("emailError", "Email already used by another user");
            return "signup-page";
        }
        else if(!email.matches(emailRegex)) {
            model.addAttribute("emailRegexError", "Incorrect email type. Correct example: asd@mail.com");
            return "signup-page";
        }
        else if(userService.isUsernameRegistered(username)) {
            model.addAttribute("usernameError", "Username not available");
            return "signup-page";
        }
        else if(!password.equals(passwordAgain)) {
            model.addAttribute("passwordError", "Passwords don't match");
            return "signup-page";
        }

        User user = new User(username, email, password);

        userService.createUser(user);

        model.addAttribute("username", makeFirstLetterCapital(user.getUsername()));
        model.addAttribute("userId", user.getId());

        return "task-list";
    }

    @GetMapping("edit-task")
    public String editTask(@RequestParam int taskId, Model model) {
        Task task = taskService.getTaskById(taskId);

        model.addAttribute("taskId", task.getTaskId());
        model.addAttribute("taskDescription", task.getTaskDescription());
        model.addAttribute("completed", task.isCompleted());
        model.addAttribute("userId", task.getUser().getId());

        return "task-editing";
    }

    @PostMapping("save-task")
    public String saveTask(@ModelAttribute Task task, Model model) {
        taskService.updateTask(task);

        User user = task.getUser();

        model.addAttribute("username", makeFirstLetterCapital(user.getUsername()));
        model.addAttribute("tasks", taskService.getTasksByUserId(user.getId()));
        model.addAttribute("userId",user.getId());

        return "task-list";
    }

    @GetMapping("/create-task")
    public String createTask(@RequestParam int userId, Model model) {
        model.addAttribute("userId", userId);

        return "task-creation";
    }

    @PostMapping("/add-task")
    public String addTask(@RequestParam String taskDescription,
                          @RequestParam(defaultValue = "false") Boolean isCompleted,
                          @RequestParam int userId,
                          Model model) {
        Task task = new Task(taskDescription, isCompleted, userService.getUserById(userId));

        taskService.addTask(task);

        model.addAttribute("username", makeFirstLetterCapital(task.getUser().getUsername()));
        model.addAttribute("tasks", taskService.getTasksByUserId(task.getUser().getId()));
        model.addAttribute("userId", task.getUser().getId());

        return "task-list";
    }

    @PostMapping("/delete-task")
    public String deleteTask(@RequestParam int taskId, Model model) {
        Task task = taskService.getTaskById(taskId);

        User user = task.getUser();

        taskService.deleteTask(task);

        model.addAttribute("username", makeFirstLetterCapital(user.getUsername()));
        model.addAttribute("tasks", taskService.getTasksByUserId(user.getId()));
        model.addAttribute("userId", user.getId());

        return "task-list";
    }

    private String makeFirstLetterCapital(String username) {
        return username.substring(0, 1).toUpperCase() + username.substring(1, username.length());
    }
}
