package com.GerenciadorTCC.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.GerenciadorTCC.entities.Task;
import com.GerenciadorTCC.repository.TaskRepository;

public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Optional<Task> findByTitle(String title){
        try {
            return Optional.ofNullable(taskRepository.findByTitle(title));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar tarefa por título: " + title);
        }        
    }
}
