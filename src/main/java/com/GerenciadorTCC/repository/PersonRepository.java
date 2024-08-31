package com.GerenciadorTCC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GerenciadorTCC.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
}
