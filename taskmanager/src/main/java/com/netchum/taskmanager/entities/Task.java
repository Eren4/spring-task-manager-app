package com.netchum.taskmanager.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_ID")
    private int id;

    @Column(name = "TASK_DESCRIPTION")
    private String taskDescription;

    @Column(name = "COMPLETED")
    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Task() {

    }

    public Task(String taskDescription, boolean isCompleted, User user) {
        this.taskDescription = taskDescription;
        this.isCompleted = isCompleted;
        this.user = user;
    }

    public int getTaskId() {
        return id;
    }

    public void setTaskId(int taskId) {
        this.id = taskId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public User getUserId() {
        return user;
    }

    public void setUserId(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + id +
                ", taskDescription='" + taskDescription + '\'' +
                ", isCompleted=" + isCompleted +
                ", user=" + user +
                '}';
    }
}
