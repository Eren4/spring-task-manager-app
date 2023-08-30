package com.netchum.taskmanager.services;

import com.netchum.taskmanager.entities.Task;
import com.netchum.taskmanager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByUserId(int id) {
        return taskRepository.findByUser_Id(id);
    }

    public Task getTaskById(int id) {
        return taskRepository.findById(id);
    }

    public void updateTask(Task task) {
        taskRepository.save(task);
    }
}
