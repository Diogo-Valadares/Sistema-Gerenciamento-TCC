package com.GerenciadorTCC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GerenciadorTCC.entities.WorkType;

@Repository
public interface WorkTypeRepository extends JpaRepository<WorkType, Long> {
    
}
