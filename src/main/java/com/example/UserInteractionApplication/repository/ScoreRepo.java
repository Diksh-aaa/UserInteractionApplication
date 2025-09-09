package com.example.UserInteractionApplication.repository;

import com.example.UserInteractionApplication.model.Score;
import com.example.UserInteractionApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepo extends JpaRepository<Score, Long> {
    List<Score> findByUser(User user);
}
