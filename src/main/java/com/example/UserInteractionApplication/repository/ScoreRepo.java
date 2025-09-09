package com.example.UserInteractionApplication.repository;

import com.example.UserInteractionApplication.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScoreRepo extends JpaRepository<Score, Long> {
    List<Score> findTop3ByOrderByValueDesc();  // 👈 field must match entity
}
