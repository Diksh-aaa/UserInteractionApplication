package com.example.UserInteractionApplication.repository;

import com.example.UserInteractionApplication.model.Score;
import com.example.UserInteractionApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepo extends JpaRepository<Score, Long> {

    // scores for a specific user
    List<Score> findByUser(User user);

    // top 3 scores
    List<Score> findTop3ByOrderByValueDesc();
}
