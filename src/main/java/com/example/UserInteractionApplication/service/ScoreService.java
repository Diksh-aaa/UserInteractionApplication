package com.example.UserInteractionApplication.service;

import com.example.UserInteractionApplication.model.Score;
import com.example.UserInteractionApplication.model.User;
import com.example.UserInteractionApplication.repository.ScoreRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ScoreService {

    private final ScoreRepo scoreRepo;

    public ScoreService(ScoreRepo scoreRepo) { this.scoreRepo = scoreRepo; }

    public Score addScore(User user, int points) {
        Score score = new Score();
        score.setUser(user);
        score.setPoints(points);
        return scoreRepo.save(score);
    }

    public List<Score> getScoresForUser(UUID userId) {
        return scoreRepo.findByUserId(userId);
    }
}
