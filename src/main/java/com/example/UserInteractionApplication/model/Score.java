package com.example.UserInteractionApplication.model;

import jakarta.persistence.*;

@Entity
@Table(name = "scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value;  // 👈 important, matches repository field name

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
