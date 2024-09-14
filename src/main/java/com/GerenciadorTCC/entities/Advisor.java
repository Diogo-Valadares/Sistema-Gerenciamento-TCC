package com.GerenciadorTCC.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Advisor extends Person{

    public Advisor() {        
    }    
    public Advisor(long id) {
        super(id);
    }
    
   @OneToMany(mappedBy="advisor")
    private List<AcademicWork> academicWorks;

    public List<AcademicWork> getAcademicWorks() {
        return academicWorks;
    }
    public void setAcademicWorks(List<AcademicWork> academicWorks) {
        this.academicWorks = academicWorks;
    }

    @NotBlank(message="A especialidade do orientador é obrigatória")
    @Max(value=50, message="A especialidade do orientador deve ter no máximo 50 caracteres")
    @Column(nullable=false, length=50)
    private String speciality;

    @NotBlank(message="A disponibilidade do orientador é obrigatória")
    @Max(value=500, message="A disponibilidade do orientador deve ter no máximo 500 caracteres")
    @Column(nullable=false, length=500)
    private String disponibility;
    
    public String getSpeciality() {
        return speciality;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    public String getDisponibility() {
        return disponibility;
    }
    public void setDisponibility(String disponibility) {
        this.disponibility = disponibility;
    }
}
