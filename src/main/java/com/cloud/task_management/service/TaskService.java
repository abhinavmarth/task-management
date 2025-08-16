package com.cloud.task_management.service;

import com.cloud.task_management.model.Task;
import com.cloud.task_management.model.TaskStatus;
import com.cloud.task_management.model.User;
import com.cloud.task_management.repository.TaskRepository;
import com.cloud.task_management.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskrepo;
    private final UserRepository userepo;

    public TaskService(TaskRepository task, UserRepository userepo){
        this.taskrepo = task;
        this.userepo = userepo;
    }

    private User getCurrentUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not Found: " + username));
    }

    public Task createTask(Task task){
        User user = getCurrentUser();
        task.setUser(user);
        task.setStatus(TaskStatus.PENDING);
        return taskrepo.save(task);
    }

    public List<Task> getMyTasks(){
        User user = getCurrentUser();
        return taskrepo.findByUserId(user.getId());
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskrepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        if (!task.getUser().getId().equals(getCurrentUser().getId())) {
            throw new IllegalArgumentException("Unauthorized");
        }

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setDueDate(updatedTask.getDueDate());

        return taskrepo.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskrepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        if (!task.getUser().getId().equals(getCurrentUser().getId())) {
            throw new IllegalArgumentException("Unauthorized");
        }

        taskrepo.delete(task);
    }
}
