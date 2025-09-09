package com.example.UserInteractionApplication.service;

import com.example.UserInteractionApplication.model.Score;
import com.example.UserInteractionApplication.repository.ScoreRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    private final ScoreRepo scoreRepository;

    public ScoreService(ScoreRepo scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public Score createScore(Score score) {
        return scoreRepository.save(score);
    }

    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    // 🔥 Fetch top 3 scores directly from DB
    public List<Score> getTop3Scores() {
        return scoreRepository.findTop3ByOrderByValueDesc();
    }
}
