package com.GerenciadorTCC.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GerenciadorTCC.entities.Person;
import com.GerenciadorTCC.repository.PersonRepository;

import jakarta.transaction.Transactional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    
    public Optional<Person> findByName(String name){
        try {
            return Optional.ofNullable(personRepository.findByName(name));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoa por nome: " + name + "\n" + e.getMessage());
        }        
    }
    public Optional<Person> findByEmail(String email){
        try {
            return Optional.ofNullable(personRepository.findByEmail(email));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoa por e-mail: " + email + "\n" + e.getMessage());
        }        
    }
    public Optional<Person> findByCpf(String cpf){
        try {
            return Optional.ofNullable(personRepository.findByCpf(cpf));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoa por CPF: " + cpf + "\n" + e.getMessage());
        }        
    }
    public Optional<Person> findByRg(String rg){
        try {
            return Optional.ofNullable(personRepository.findByRg(rg));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoa por RG: " + rg + "\n" + e.getMessage());
        }        
    }

    @Transactional
    public Person save(Person person){
        try {
            return personRepository.save(person);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar pessoa: " + person.getName() + "\n" + e.getMessage());
        }        
    }
}
