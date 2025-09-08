package com.example.UserInteractionApplication.controller;

import com.example.UserInteractionApplication.model.Score;
import com.example.UserInteractionApplication.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) { this.scoreService = scoreService; }

    @PostMapping("/{userId}")
    public Score addScore(@PathVariable UUID userId, @RequestParam int points) {
        return scoreService.addScore(userId, points);
    }

    @GetMapping
    public List<Score> getAllScores() { return scoreService.getAllScores(); }
}

