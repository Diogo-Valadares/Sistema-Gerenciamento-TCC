package com.GerenciadorTCC.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

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
    @Max(value=500, message="A descrição do tipo de trabalho deve ter no máximo 500 caracteres")
    @Column(nullable = false, length = 500)
    private String description;

    @OneToOne(mappedBy = "workType")
    private AcademicWork academicWork;
    
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
    public AcademicWork getAcademicWork() {
        return academicWork;
    }
    public void setAcademicWork(AcademicWork academicWork) {
        this.academicWork = academicWork;
    }
}
