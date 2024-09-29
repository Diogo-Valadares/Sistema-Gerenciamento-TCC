package com.GerenciadorTCC.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class WorkType implements Serializable {

    public WorkType() {
    }
    public WorkType(long id) {
        this.id = id;
    }
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message="O nome do tipo de trabalho é obrigatório")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message="A descrição do tipo de trabalho é obrigatória")
    @Size(max=500, message="A descrição do tipo de trabalho deve ter no máximo 500 caracteres")
    @Column(nullable = false, length = 500)
    private String description;

    @OneToMany(mappedBy = "workType")
    private List<AcademicWork> academicWorks;
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<AcademicWork> getAcademicWork() {
        return academicWorks;
    }
    public void setAcademicWork(List<AcademicWork> academicWorks) {
        this.academicWorks = academicWorks;
    }
}
