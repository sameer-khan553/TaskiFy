package com.taskify.controller;

import com.taskify.dto.TaskDTO;
import com.taskify.model.Task;
import com.taskify.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // CREATE
    @PostMapping
    public TaskDTO createTask(@RequestBody TaskDTO dto,
                              Authentication authentication) {

        return taskService.createTask(dto, authentication.getName());
    }

    // READ ✅ FIXED
    @GetMapping
    public List<TaskDTO> getUserTasks(Authentication authentication) {

        return taskService.getUserTasks(authentication.getName());
    }

    // UPDATE ✅ FIXED
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,
                           @RequestBody Task task,
                           Authentication authentication) {

        return taskService.updateTask(id, task, authentication.getName());
    }

    // DELETE ✅ FIXED
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id,
                             Authentication authentication) {

        taskService.deleteTask(id, authentication.getName());
        return "Task deleted successfully";
    }
}