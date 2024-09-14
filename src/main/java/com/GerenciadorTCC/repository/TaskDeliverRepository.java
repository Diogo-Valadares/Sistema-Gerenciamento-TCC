package com.GerenciadorTCC.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.GerenciadorTCC.entities.TaskDeliver;

@Repository
public interface TaskDeliverRepository extends JpaRepository<TaskDeliver, Long> {
    
    @Query(value = "SELECT td FROM TaskDeliver td WHERE td.date BETWEEN ?1 AND ?2", nativeQuery = true)
    public TaskDeliver findByDateRange(LocalDate start, LocalDate end);
}
