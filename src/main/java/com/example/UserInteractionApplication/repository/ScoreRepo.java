package com.example.UserInteractionApplication.repository;

import com.example.UserInteractionApplication.model.Score;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScoreRepo extends MongoRepository<Score, String> {

}

