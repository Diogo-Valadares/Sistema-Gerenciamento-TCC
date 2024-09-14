package com.GerenciadorTCC.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.GerenciadorTCC.entities.TaskDeliver;
import com.GerenciadorTCC.repository.TaskDeliverRepository;

public class TaskDeliverService {

    @Autowired
    private TaskDeliverRepository taskDeliverRepository;

    public Optional<TaskDeliver> findByDateRange(LocalDate startDate, LocalDate endDate){
        try {
            return Optional.ofNullable(taskDeliverRepository.findByDateRange(startDate, endDate));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar entrega de tarefa por intervalo de datas: " + startDate + " - " + endDate);
        }        
    }
}
