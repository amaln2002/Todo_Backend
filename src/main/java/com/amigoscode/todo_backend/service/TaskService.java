package com.amigoscode.todo_backend.service;

import com.amigoscode.todo_backend.Task;
import com.amigoscode.todo_backend.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByDate(LocalDate date) {
        return taskRepository.findByTaskDate(date);
    }

    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByTaskStatus(status);
    }

    public Task addTask(Task task) {
        task.setId(null);
        if (task.getTaskStatus() == null || task.getTaskStatus().isBlank()) {
            task.setTaskStatus("NOT_COMPLETED");
        }
        return taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public List<Task> getTasksBetween(LocalDate start, LocalDate end) {
        return taskRepository.findByTaskDateBetween(start, end);
    }

    @Transactional
    public List<Task> ToggleTasksAfter(LocalDate after) {
        List<Task> tasksToggle = taskRepository.findByTaskDateIsAfter(after);
        for (Task task : tasksToggle) {
            task.setTaskStatus("COMPLETED");
            taskRepository.save(task);
        }
        return tasksToggle;
    }
    @Transactional
    public void deleteTasksBefore(LocalDate before) {
        List<Task> tasksToDelete = taskRepository.findByTaskDateIsBefore(before);
        for (Task task : tasksToDelete) {
            taskRepository.deleteById(task.getId());
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task toggleTaskStatus(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if ("COMPLETED".equals(task.getTaskStatus())) {
            task.setTaskStatus("NOT_COMPLETED");
        } else {
            task.setTaskStatus("COMPLETED");
        }

        return taskRepository.save(task);
    }
    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTaskText(updatedTask.getTaskText());
        return taskRepository.save(task);
    }
}