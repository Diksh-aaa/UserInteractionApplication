package com.example.UserInteractionApplication.repository;

import com.example.UserInteractionApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

}
