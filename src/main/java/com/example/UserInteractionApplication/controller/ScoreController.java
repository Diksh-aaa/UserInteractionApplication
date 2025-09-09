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

    @PostMapping("/{userId}")
    public Score addScore(@PathVariable Long userId, @RequestParam int points) {
        return scoreService.addScore(userId, points);
    }

    @GetMapping("/{userId}")
    public List<Score> getScoresByUser(@PathVariable Long userId) {
        return scoreService.getScoresByUser(userId);
    }
}
