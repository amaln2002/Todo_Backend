package com.amigoscode.todo_backend.controller;

import com.amigoscode.todo_backend.Task;
import com.amigoscode.todo_backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tasks")
//@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/date")
    public List<Task> getTasksByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return taskService.getTasksByDate(date);
    }

    @GetMapping("/status")
    public List<Task> getTasksByStatus(@RequestParam String status) {
        return taskService.getTasksByStatus(status);
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @PutMapping("/{id}/toggle")
    public Task toggleTaskStatus(@PathVariable Long id) {
        return taskService.toggleTaskStatus(id);
    }
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }
}