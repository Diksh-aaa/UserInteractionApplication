package com.example.UserInteractionApplication.service;

import com.example.UserInteractionApplication.model.User;
import com.example.UserInteractionApplication.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepository, UserRepo userRepo) {
        UserRepo userRepo1;
        userRepo1 = userRepo;
        this.userRepo = userRepo1;
    }

    public User createUser(User user) { return userRepo.save(user); }
    public List<User> getAllUsers() { return userRepo.findAll(); }
    public Optional<User> getUserById(UUID id) { return userRepo.findById(id); }
    public void deleteUser(UUID id) { userRepo.deleteById(id); }
}

