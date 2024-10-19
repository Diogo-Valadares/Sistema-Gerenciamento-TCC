package com.GerenciadorTCC.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GerenciadorTCC.entities.WorkType;
import com.GerenciadorTCC.repository.WorkTypeRepository;

@Service
public class WorkTypeService {

    @Autowired
    private WorkTypeRepository workTypeRepository;

    public Optional<WorkType> findById(long id){
        try {
            return workTypeRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar tipo de trabalho por id: " + id);
        }
    }

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

    public List<WorkType> findAll(){
        try {
            return workTypeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todos os tipos de trabalho: " + e.getMessage());
        }
    }
}
