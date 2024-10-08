package com.GerenciadorTCC.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GerenciadorTCC.entities.AcademicWork;
import com.GerenciadorTCC.repository.AcademicWorkRepository;

import jakarta.transaction.Transactional;

@Service
public class AcademicWorkService {

    @Autowired
    private AcademicWorkRepository academicWorkRepository;

    public Optional<AcademicWork> findByTitle(String title){
        try {
            return Optional.ofNullable(academicWorkRepository.findByTitle(title));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar trabalho acadêmico por título: " + title + "\n" + e.getMessage());
        }        
    }
    public Optional<AcademicWork> findByStudentName(String name){
        try {
            return Optional.ofNullable(academicWorkRepository.findByStudentName(name));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar trabalho acadêmico por nome do estudante: " + name + "\n" + e.getMessage());
        }        
    }
    public Optional<AcademicWork> findByAdvisorName(String name){
        try {
            return Optional.ofNullable(academicWorkRepository.findByAdvisorName(name));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar trabalho acadêmico por nome do orientador: " + name + "\n" + e.getMessage());
        }        
    }

    
    @Transactional
    public AcademicWork save(AcademicWork academicWork){
        try {
            return academicWorkRepository.save(academicWork);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar trabalho acadêmico: " + academicWork.getTitle() + "\n" + e.getMessage());
        }        
    }
}
