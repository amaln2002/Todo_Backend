package com.amigoscode.todo_backend.repository;

import com.amigoscode.todo_backend.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTaskStatus(String taskStatus);
    List<Task> findByTaskDate(LocalDate taskDate);
    List<Task> findByTaskDateBetween(LocalDate start, LocalDate end);
    List<Task> findByTaskDateIsBefore(LocalDate before);
    List<Task> findByTaskDateIsAfter(LocalDate after);

}