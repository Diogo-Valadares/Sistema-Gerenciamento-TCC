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

    public Optional<AcademicWork> findById(Long id) {
        try {
            return academicWorkRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar trabalho acadêmico por ID: " + id + "\n" + e.getMessage());
        }
    }
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
    public void deleteById(Long id) {
        try {
            academicWorkRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar trabalho acadêmico por ID: " + id + "\n" + e.getMessage());
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

    @Transactional
    public AcademicWork update(AcademicWork updatedAcademicWork) {
        try {
            Optional<AcademicWork> existingAcademicWork = academicWorkRepository.findById(updatedAcademicWork.getId()).map(a -> (AcademicWork) a);
            if (existingAcademicWork.isPresent()) {
                AcademicWork existingAcademicWorkToUpdate = existingAcademicWork.get();
                existingAcademicWorkToUpdate.setTitle(updatedAcademicWork.getTitle());
                existingAcademicWorkToUpdate.setStudent(updatedAcademicWork.getStudent());
                existingAcademicWorkToUpdate.setAdvisor(updatedAcademicWork.getAdvisor());
                existingAcademicWorkToUpdate.setWorkType(updatedAcademicWork.getWorkType());
                existingAcademicWorkToUpdate.setEndDate(updatedAcademicWork.getEndDate());
                return academicWorkRepository.save(existingAcademicWorkToUpdate);
            } else {
                throw new RuntimeException("Trabalho acadêmico não encontrado com título: " + updatedAcademicWork.getTitle());
            }
        }catch (IllegalArgumentException e) {
            throw new RuntimeException("Entidade nula fornecida para atualização do trabalho acadêmico: " + updatedAcademicWork.getTitle() + "\n" + e.getMessage());
        } catch (org.springframework.dao.OptimisticLockingFailureException e) {
            throw new RuntimeException("Erro de bloqueio otimista ao atualizar trabalho acadêmico: " + updatedAcademicWork.getTitle() + "\n" + e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao atualizar trabalho acadêmico: " + updatedAcademicWork.getTitle() + "\n" + e.getMessage());
        }
    }
}
