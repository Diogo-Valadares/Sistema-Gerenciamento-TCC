package com.GerenciadorTCC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.GerenciadorTCC.entities.Avaliation;

@Repository
public interface AvaliationRepository extends JpaRepository<Avaliation, Long> {
    @Query("SELECT d.avaliation FROM Document d WHERE d.title = ?1")
    Avaliation getAvaliation(String title);
}
