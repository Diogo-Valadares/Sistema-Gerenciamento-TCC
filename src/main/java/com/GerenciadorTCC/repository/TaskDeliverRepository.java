package com.GerenciadorTCC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GerenciadorTCC.entities.TaskDeliver;

@Repository
public interface TaskDeliverRepository extends JpaRepository<TaskDeliver, Long> {
    
}
