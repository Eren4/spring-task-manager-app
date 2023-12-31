package com.netchum.taskmanager.services;

import com.netchum.taskmanager.entities.User;
import com.netchum.taskmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if(user != null) {
            return passwordEncoder.matches(password, user.getPassword());
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
