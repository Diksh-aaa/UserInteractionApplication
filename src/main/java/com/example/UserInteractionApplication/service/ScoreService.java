package com.example.UserInteractionApplication.service;

import com.example.UserInteractionApplication.model.Score;
import com.example.UserInteractionApplication.model.User;
import com.example.UserInteractionApplication.repository.ScoreRepo;
import com.example.UserInteractionApplication.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {
    private final ScoreRepo scoreRepo;
    private final UserRepo userRepo;

    public ScoreService(ScoreRepo scoreRepo, UserRepo userRepo) {
        this.scoreRepo = scoreRepo;
        this.userRepo = userRepo;
    }

    public List<Score> getScoresByUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        return scoreRepo.findByUser(user);
    }

    public Score addScore(Long userId, int points) {
        User user = userRepo.findById(userId).orElseThrow();
        Score score = new Score();
        score.setUser(user);
        score.setPoints(points);
        return scoreRepo.save(score);
    }
}
