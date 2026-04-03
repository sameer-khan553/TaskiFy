package com.taskify.service;

import com.taskify.dto.TaskDTO;
import com.taskify.model.Task;
import com.taskify.model.User;
import com.taskify.repository.TaskRepository;

import com.taskify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    // CREATE
    public TaskDTO createTask(TaskDTO dto, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = convertToEntity(dto);
        task.setUser(user);

        Task saved = taskRepository.save(task);

        return convertToDTO(saved);
    }

    // READ - Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // READ - Get user's tasks
    public List<TaskDTO> getUserTasks(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Task> tasks = taskRepository.findByUser(user);
        return tasks.stream().map(this::convertToDTO).toList();
    }

    // UPDATE - Generic update
    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.isCompleted());

        return taskRepository.save(task);
    }

    // UPDATE - With user validation
    public Task updateTask(Long id, Task updatedTask, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Verify that the task belongs to the user
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized: Task does not belong to the user");
        }

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.isCompleted());

        return taskRepository.save(task);
    }

    // DELETE - Generic delete
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // DELETE - With user validation
    public void deleteTask(Long id, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Verify that the task belongs to the user
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized: Task does not belong to the user");
        }

        taskRepository.deleteById(id);
    }

    private TaskDTO convertToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.isCompleted());
        return dto;
    }

    private Task convertToEntity(TaskDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.isCompleted());
        return task;
    }

}