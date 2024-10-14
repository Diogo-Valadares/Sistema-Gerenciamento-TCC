package com.GerenciadorTCC.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GerenciadorTCC.entities.Task;
import com.GerenciadorTCC.repository.TaskRepository;

import jakarta.transaction.Transactional;

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

    public Optional<Task> findById(Long id) {
        try {
            return taskRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar tarefa por ID: " + id);
        }
    }

    @Transactional
    public Task save(Task task){
        try {
            return taskRepository.save(task);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar tarefa: " + task.getTitle() + "\n" + e.getMessage());
        }        
    }

    @Transactional
    public Task update(Task updatedTask) {
        try {
            Optional<Task> existingTask = taskRepository.findById(updatedTask.getId());
            if (existingTask.isPresent()) {
                Task existingTaskToUpdate = existingTask.get();
                existingTaskToUpdate.setTitle(updatedTask.getTitle());
                existingTaskToUpdate.setDescription(updatedTask.getDescription());
                existingTaskToUpdate.setDeadline(updatedTask.getDeadline());
                existingTaskToUpdate.setStatus(updatedTask.getStatus());
                existingTaskToUpdate.setAcademicWork(updatedTask.getAcademicWork());
                return taskRepository.save(existingTaskToUpdate);
            } else {
                throw new RuntimeException("Tarefa não encontrada com ID: " + updatedTask.getId());
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Entidade nula fornecida para atualização da tarefa: " + updatedTask.getTitle() + "\n" + e.getMessage());
        } catch (org.springframework.dao.OptimisticLockingFailureException e) {
            throw new RuntimeException("Erro de bloqueio otimista ao atualizar tarefa: " + updatedTask.getTitle() + "\n" + e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao atualizar tarefa: " + updatedTask.getTitle() + "\n" + e.getMessage());
        }
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            if (taskRepository.existsById(id)) {
                taskRepository.deleteById(id);
            } else {
                throw new RuntimeException("Tarefa não encontrada: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar tarefa por ID: " + id + "\n" + e.getMessage());
        }
    }
}
