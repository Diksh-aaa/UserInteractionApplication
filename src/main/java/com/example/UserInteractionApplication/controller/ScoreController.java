package com.example.UserInteractionApplication.controller;

import com.example.UserInteractionApplication.model.Score;
import com.example.UserInteractionApplication.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    // Inner class for leaderboard response (no separate DTO file)
    public static class LeaderboardEntry {
        private String username;
        private int score;

        public LeaderboardEntry(String username, int score) {
            this.username = username;
            this.score = score;
        }

        public String getUsername() {
            return username;
        }

        public int getScore() {
            return score;
        }
    }

    @PostMapping
    public Score createScore(@RequestBody Score score) {
        return scoreService.createScore(score);
    }

    @GetMapping
    public List<Score> getAllScores() {
        return scoreService.getAllScores();
    }

    // 🔥 Leaderboard API
    @GetMapping("/leaderboard")
    public List<LeaderboardEntry> getTop3Scores() {
        return scoreService.getTop3Scores()
                .stream()
                .map(score -> new LeaderboardEntry(
                        score.getUser().getName(),
                        score.getValue()
                ))
                .toList();
    }
}
