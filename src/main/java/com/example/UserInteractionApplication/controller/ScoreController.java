package com.example.UserInteractionApplication.controller;

import com.example.UserInteractionApplication.model.Score;
import com.example.UserInteractionApplication.model.User;
import com.example.UserInteractionApplication.repository.UserRepo;
import com.example.UserInteractionApplication.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    private final ScoreService scoreService;
    private final UserRepo userRepository;

    public ScoreController(ScoreService scoreService, UserRepo userRepository) {
        this.scoreService = scoreService;
        this.userRepository = userRepository;
    }

    // Inner class to return user info + score
    public static class PlayerScore {
        private Long userId;
        private String username;
        private int score;

        public PlayerScore(Long userId, String username, int score) {
            this.userId = userId;
            this.username = username;
            this.score = score;
        }

        public Long getUserId() { return userId; }
        public String getUsername() { return username; }
        public int getScore() { return score; }
    }

    // POST score by userId
    @PostMapping
    public PlayerScore postScore(@RequestParam Long userId, @RequestParam int scoreValue) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Score score = new Score();
        score.setUser(user);
        score.setValue(scoreValue);

        Score savedScore = scoreService.createScore(score);
        return new PlayerScore(user.getId(), user.getName(), savedScore.getValue());
    }

    // GET scores for a specific user by userId
    @GetMapping
    public List<PlayerScore> getScoresByUser(@RequestParam Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return scoreService.getScoresByUser(user).stream()
                .map(score -> new PlayerScore(user.getId(), user.getName(), score.getValue()))
                .toList();
    }

    // GET leaderboard - top 3 scores
    @GetMapping("/leaderboard")
    public List<PlayerScore> getTop3Scores() {
        return scoreService.getTop3Scores().stream()
                .map(score -> new PlayerScore(
                        score.getUser().getId(),
                        score.getUser().getName(),
                        score.getValue()
                ))
                .toList();
    }
}
