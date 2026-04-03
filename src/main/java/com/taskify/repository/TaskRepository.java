package com.taskify.repository;

import com.taskify.model.Task;
import com.taskify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(User user);

}
