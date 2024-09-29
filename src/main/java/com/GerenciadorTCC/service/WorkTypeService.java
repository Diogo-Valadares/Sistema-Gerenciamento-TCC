package com.GerenciadorTCC.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GerenciadorTCC.entities.WorkType;
import com.GerenciadorTCC.repository.WorkTypeRepository;

@Service
public class WorkTypeService {

    @Autowired
    private WorkTypeRepository workTypeRepository;

    public Optional<String> findDescriptionByName(String name){
        try {
            return Optional.ofNullable(workTypeRepository.findDescriptionByName(name));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar tipo de trabalho por nome: " + name);
        }        
    }
    public Optional<WorkType> findByName(String name){
        try {
            return Optional.ofNullable(workTypeRepository.findByName(name));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar tipo de trabalho por nome: " + name);
        }        
    }
    public WorkType save(WorkType workType){
        try {
            return workTypeRepository.save(workType);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar tipo de trabalho: " + workType.getName() + "\n" + e.getMessage());
        }        
    }
}
