package com.netchum.taskmanager.repositories;

import com.netchum.taskmanager.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findAll();

    List<Task> findByUser_Id(int userId);
}
