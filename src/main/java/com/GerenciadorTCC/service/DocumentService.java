package com.GerenciadorTCC.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GerenciadorTCC.entities.Document;
import com.GerenciadorTCC.repository.DocumentRepository;

import jakarta.transaction.Transactional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Optional<Document> findByTitle(String title){
        try {
            return Optional.ofNullable(documentRepository.findByTitle(title));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar documento por título: " + title);
        }        
    }

    @Transactional
    public Document save(Document document){
        try {
            return documentRepository.save(document);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar documento: " + document.getTitle() + "\n" + e.getMessage());
        }        
    }
}
