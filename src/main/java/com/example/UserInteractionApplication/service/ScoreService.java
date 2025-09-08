package com.example.UserInteractionApplication.service;

import com.example.UserInteractionApplication.model.Score;
import com.example.UserInteractionApplication.repository.ScoreRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ScoreService {

    private final ScoreRepo scoreRepo;

    public ScoreService(ScoreRepo scoreRepository) {
        this.scoreRepo = scoreRepository;
    }

    public Score addScore(UUID userId, int points) {
        Score score = new Score();
        score.setUserId(userId);
        score.setPoints(points);
        return scoreRepo.save(score);
    }

    public List<Score> getAllScores() { return scoreRepo.findAll(); }
}
