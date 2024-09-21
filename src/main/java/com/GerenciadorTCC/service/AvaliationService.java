package com.GerenciadorTCC.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GerenciadorTCC.entities.Avaliation;
import com.GerenciadorTCC.repository.AvaliationRepository;

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
}
