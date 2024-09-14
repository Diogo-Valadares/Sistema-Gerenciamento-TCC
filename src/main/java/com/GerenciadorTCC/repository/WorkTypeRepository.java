package com.GerenciadorTCC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.GerenciadorTCC.entities.WorkType;

@Repository
public interface WorkTypeRepository extends JpaRepository<WorkType, Long> {
    @Query("SELECT wt.description FROM WorkType wt WHERE wt.name = ?1")
    public String findDescriptionByName(String name);
    @Query("SELECT wt FROM WorkType wt WHERE wt.name = ?1")
    public WorkType findByName(String name);
}
