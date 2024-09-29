package com.GerenciadorTCC.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GerenciadorTCC.entities.TaskDeliver;
import com.GerenciadorTCC.repository.TaskDeliverRepository;

@Service
public class TaskDeliverService {

    @Autowired
    private TaskDeliverRepository taskDeliverRepository;

    public Optional<TaskDeliver> findByDateRange(LocalDate startDate, LocalDate endDate){
        try {
            return Optional.ofNullable(taskDeliverRepository.findByDateRange(startDate, endDate));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar entrega de tarefa por intervalo de datas\n" + e.getMessage());
        }        
    }
    public TaskDeliver save(TaskDeliver taskDeliver){
        try {
            return taskDeliverRepository.save(taskDeliver);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar entrega de tarefa: " + taskDeliver.getTask().getTitle() + 
            " | " + taskDeliver.getDocument().getTitle() + "\n" + e.getMessage());
        }        
    }
}
