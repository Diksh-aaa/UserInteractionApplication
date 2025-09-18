// src/main/java/com/example/UserInteractionApplication/model/User.java
package com.example.UserInteractionApplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // This field stores the user's name

    // REMOVED: Redundant manual setter
    // public void setName(String name) { }
}