package com.GerenciadorTCC.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GerenciadorTCC.entities.Avaliation;
import com.GerenciadorTCC.repository.AvaliationRepository;

import jakarta.transaction.Transactional;

@Service
public class AvaliationService {

    @Autowired
    private AvaliationRepository avaliationRepository;

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
}
