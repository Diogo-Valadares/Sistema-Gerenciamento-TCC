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

    public Optional<Document> findById(Long id) {
        try {
            return documentRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar documento por ID: " + id);
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

    @Transactional
    public Document update(Document updatedDocument) {
        try {
            Optional<Document> existingDocument = documentRepository.findById(updatedDocument.getId());
            if (existingDocument.isPresent()) {
                Document existingDocumentToUpdate = existingDocument.get();
                existingDocumentToUpdate.setTitle(updatedDocument.getTitle());
                existingDocumentToUpdate.setContent(updatedDocument.getContent());
                existingDocumentToUpdate.setCitation(updatedDocument.getCitation());
                existingDocumentToUpdate.setUploadDate(updatedDocument.getUploadDate());
                existingDocumentToUpdate.setAcademicWork(updatedDocument.getAcademicWork());
                existingDocumentToUpdate.setAvaliation(updatedDocument.getAvaliation());
                return documentRepository.save(existingDocumentToUpdate);
            } else {
                throw new RuntimeException("Documento não encontrado com ID: " + updatedDocument.getId());
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Entidade nula fornecida para atualização do documento: " + updatedDocument.getTitle() + "\n" + e.getMessage());
        } catch (org.springframework.dao.OptimisticLockingFailureException e) {
            throw new RuntimeException("Erro de bloqueio otimista ao atualizar documento: " + updatedDocument.getTitle() + "\n" + e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao atualizar documento: " + updatedDocument.getTitle() + "\n" + e.getMessage());
        }
    }
    @Transactional
    public void deleteById(Long id) {
        try {
            if (documentRepository.existsById(id)) {
                documentRepository.deleteById(id);
            } else {
                throw new RuntimeException("Documento não encontrado com ID: " + id);
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Argumento inválido ao deletar documento por ID: " + id + "\n" + e.getMessage());
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            throw new RuntimeException("Documento não encontrado ao deletar por ID: " + id + "\n" + e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao deletar documento por ID: " + id + "\n" + e.getMessage());
        }
    }

}
