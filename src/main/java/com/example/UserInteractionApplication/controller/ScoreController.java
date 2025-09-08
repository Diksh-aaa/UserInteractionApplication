package com.example.UserInteractionApplication.controller;

import com.example.UserInteractionApplication.model.Score;
import com.example.UserInteractionApplication.model.User;
import com.example.UserInteractionApplication.service.ScoreService;
import com.example.UserInteractionApplication.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    private final ScoreService scoreService;
    private final UserService userService;

    public ScoreController(ScoreService scoreService, UserService userService) {
        this.scoreService = scoreService;
        this.userService = userService;
    }

    @PostMapping("/{userId}")
    public Score addScore(@PathVariable UUID userId, @RequestParam int points) {
        Optional<User> userOpt = userService.getUserById(userId);
        if (userOpt.isPresent()) {
            return scoreService.addScore(userOpt.get(), points);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @GetMapping("/{userId}")
    public List<Score> getScoresForUser(@PathVariable UUID userId) {
        return scoreService.getScoresForUser(userId);
    }
}
