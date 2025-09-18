// src/main/java/com/example/UserInteractionApplication/service/UserService.java
package com.example.UserInteractionApplication.service;

import com.example.UserInteractionApplication.model.User;
import com.example.UserInteractionApplication.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Retrieves all users. (Kept for completeness of a UserService)
     * @return A list of all User objects.
     */
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    /**
     * Creates and saves a new User.
     * Includes basic validation for the user's name.
     * @param user The User object to save.
     * @return The saved User object (with generated ID).
     * @throws IllegalArgumentException if the user's name is null or empty.
     */
    public User createUser(User user) {
        if (user == null || user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("User and user name cannot be null or empty.");
        }
        return userRepo.save(user);
    }

    /**
     * Retrieves a User by their ID. (Kept for completeness of a UserService)
     * @param id The ID of the user to retrieve.
     * @return The User object if found.
     * @throws java.util.NoSuchElementException if the user with the given ID is not found.
     */
    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new java.util.NoSuchElementException("User not found with ID: " + id));
    }

    /**
     * Retrieves the user with the largest ID.
     * This method is private as it's an internal helper for getLargestUserId().
     * @return An Optional containing the User object if found, or empty if no users exist.
     */
    private Optional<User> getUserWithLargestIdInternal() {
        User user = userRepo.findTopByOrderByIdDesc();
        return Optional.ofNullable(user);
    }

    /**
     * Retrieves the ID of the user with the largest ID.
     * @return An Optional containing the largest User ID if found, or empty if no users exist.
     */
    public Optional<Long> getLargestUserId() {
        return getUserWithLargestIdInternal()
                .map(User::getId);
    }
}