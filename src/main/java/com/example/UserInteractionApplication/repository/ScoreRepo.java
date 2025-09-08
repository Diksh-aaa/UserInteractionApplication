package com.example.UserInteractionApplication.repository;

import com.example.UserInteractionApplication.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ScoreRepo extends JpaRepository<Score, UUID> {
    List<Score> findByUserId(UUID userId);
}
