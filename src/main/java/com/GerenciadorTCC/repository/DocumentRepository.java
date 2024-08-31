package com.GerenciadorTCC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GerenciadorTCC.entities.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    
}
