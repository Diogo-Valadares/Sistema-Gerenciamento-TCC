package com.GerenciadorTCC.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GerenciadorTCC.entities.Avaliation;
import com.GerenciadorTCC.entities.Document;
import com.GerenciadorTCC.repository.AvaliationRepository;
import com.GerenciadorTCC.repository.DocumentRepository;

import jakarta.transaction.Transactional;

@Service
public class AvaliationService {

    @Autowired
    private AvaliationRepository avaliationRepository;
    @Autowired
    private DocumentRepository documentRepository;

    public Optional<Avaliation> findByTitle(String title){
        try {
            return Optional.ofNullable(avaliationRepository.findByTitle(title));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar avaliação por título: " + title);
        }        
    }

    @Transactional
    public Avaliation save(Avaliation avaliation){
        try {
            return avaliationRepository.save(avaliation);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar anotação: " + avaliation.getAnnotation() + "\n" + e.getMessage());
        }        
    }
    @Transactional
    public void addAvaliationToDocument(Long documentId, Avaliation avaliation) {
        try {
            Optional<Document> documentOpt = documentRepository.findById(documentId);
            if (documentOpt.isPresent()) {
                var savedAvaliation = avaliationRepository.save(avaliation);
                Document document = documentOpt.get();
                document.setAvaliation(savedAvaliation);
                documentRepository.save(document);
            } else {
                throw new RuntimeException("Documento não encontrado: " + documentId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar avaliação ao documento: " + documentId + "\n" + e.getMessage());
        }
    }
}
