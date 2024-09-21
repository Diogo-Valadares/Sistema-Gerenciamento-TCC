package com.GerenciadorTCC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.GerenciadorTCC.entities.AcademicWork;

@Repository
public interface AcademicWorkRepository extends JpaRepository<AcademicWork, Long> {
    @Query("SELECT aw FROM AcademicWork aw WHERE aw.title = ?1")
    public AcademicWork findByTitle(String title);
    @Query("SELECT aw FROM AcademicWork aw WHERE aw.student.name = ?1")
    public AcademicWork findByStudentName(String name);
    @Query("SELECT aw FROM AcademicWork aw WHERE aw.advisor.name = ?1")
    public AcademicWork findByAdvisorName(String name);
}
