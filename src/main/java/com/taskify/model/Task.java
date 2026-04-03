package com.taskify.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private boolean completed;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public boolean isCompleted() {
        return completed;
    }

    // ✅ Setter
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}