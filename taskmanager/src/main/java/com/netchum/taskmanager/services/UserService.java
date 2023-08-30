package com.netchum.taskmanager.services;

import com.netchum.taskmanager.entities.User;
import com.netchum.taskmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if(user != null) {
            return (user.getPassword().equals(password));
        }
        else {
            return false;
        }
    }

    public boolean isEmailRegistered(String email) {
        User user = userRepository.findByEmail(email);
        if(user != null) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isUsernameRegistered(String username) {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            return true;
        }
        else {
            return false;
        }
    }

    public void createUser(User user) {
        userRepository.save(user);
    }
}
