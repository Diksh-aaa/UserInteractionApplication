// src/main/java/com/example/UserInteractionApplication/controller/UserController.java
package com.example.UserInteractionApplication.controller;

import com.example.UserInteractionApplication.model.User;
import com.example.UserInteractionApplication.service.UserService;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins ="http://localhost:4200",
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE},
        allowedHeaders = {"*"},
        maxAge=3600)
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // DTO for the POST /users request body
    // Client only needs to provide the name, ID is auto-generated
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateUserRequest {
        private String name;

        // REMOVED: Redundant manual getter and setter
        // public String getName() { return name; }
        // public void setName(String name) { this.name = name; }
    }

    // DTO for consistent error responses
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor // This annotation generates the constructor ErrorResponse(String message, int status)
    public static class ErrorResponse {
        private String message;
        private int status;

        // REMOVED: The problematic manual constructor
        // public ErrorResponse(String s, int value) {
        // }
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("User name cannot be null or empty.", HttpStatus.BAD_REQUEST.value()));
        }
        try {
            User newUser = new User();
            newUser.setName(request.getName());
            User createdUser = userService.createUser(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (IllegalArgumentException e) {
            // This catches validation errors from the service layer
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        } catch (Exception e) {
            // Catch any other unexpected errors during creation
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("An unexpected error occurred during user creation.", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    /**
     * Retrieves the ID of the user with the largest ID (latest user ID).
     * Endpoint: GET /userid
     * Response: The latest user ID (e.g., 5) with 200 OK.
     * Returns 404 NOT FOUND if no users exist.
     */
    @GetMapping("/userID")
    public ResponseEntity<?> getLatestUserId() {
        Optional<Long> latestUserId = userService.getLargestUserId();
        if (latestUserId.isPresent()) {
            return ResponseEntity.ok(latestUserId.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("No users found in the database.", HttpStatus.NOT_FOUND.value()));
        }
    }
}