package com.GerenciadorTCC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.GerenciadorTCC.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p WHERE p.name = ?1")
    public Person findByName(String name);
    @Query("SELECT p FROM Person p WHERE p.email = ?1")
    public Person findByEmail(String email);
    @Query("SELECT p FROM Person p WHERE p.cpf = ?1")
    public Person findByCpf(String cpf);
    @Query("SELECT p FROM Person p WHERE p.rg = ?1")
    public Person findByRg(String rg);
}
