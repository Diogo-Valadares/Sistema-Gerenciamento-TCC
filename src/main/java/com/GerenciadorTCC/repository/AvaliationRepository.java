package com.GerenciadorTCC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GerenciadorTCC.entities.Avaliation;

@Repository
public interface AvaliationRepository extends JpaRepository<Avaliation, Long> {
    
}
