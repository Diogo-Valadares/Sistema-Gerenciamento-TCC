package com.GerenciadorTCC.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GerenciadorTCC.entities.Task;
import com.GerenciadorTCC.repository.TaskRepository;

@Service
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
    public Task save(Task task){
        try {
            return taskRepository.save(task);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar tarefa: " + task.getTitle() + "\n" + e.getMessage());
        }        
    }
}
