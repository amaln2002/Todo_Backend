package com.amigoscode.todo_backend;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "task_text", nullable = false, length = 500)
    private String taskText;

    @Column(name = "task_date", nullable = false)
    private LocalDate taskDate;

    @Column(name = "task_status", nullable = false, length = 100)
    private String taskStatus;

}