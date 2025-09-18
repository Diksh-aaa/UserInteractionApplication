package com.example.UserInteractionApplication.repository;

import com.example.UserInteractionApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    // Finds the top (first) user when ordered by ID in descending order.
    // This effectively gets the user with the largest ID.
    User findTopByOrderByIdDesc();
}