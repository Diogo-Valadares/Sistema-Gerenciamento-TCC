package com.GerenciadorTCC.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GerenciadorTCC.entities.Person;
import com.GerenciadorTCC.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    
    public Optional<Person> findByName(String name){
        try {
            return Optional.ofNullable(personRepository.findByName(name));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoa por nome: " + name);
        }        
    }
    public Optional<Person> findByEmail(String email){
        try {
            return Optional.ofNullable(personRepository.findByName(email));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoa por e-mail: " + email);
        }        
    }
    public Optional<Person> findByCpf(String cpf){
        try {
            return Optional.ofNullable(personRepository.findByName(cpf));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoa por CPF: " + cpf);
        }        
    }
    public Optional<Person> findByRg(String rg){
        try {
            return Optional.ofNullable(personRepository.findByName(rg));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoa por rg: " + rg);
        }        
    }

}
