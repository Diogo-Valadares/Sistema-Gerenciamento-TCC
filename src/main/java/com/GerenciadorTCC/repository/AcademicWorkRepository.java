package com.GerenciadorTCC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GerenciadorTCC.entities.AcademicWork;

@Repository
public interface AcademicWorkRepository extends JpaRepository<AcademicWork, Long> {
    
}
