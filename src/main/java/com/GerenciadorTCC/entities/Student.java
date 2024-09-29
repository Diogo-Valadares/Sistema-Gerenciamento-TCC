package com.GerenciadorTCC.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Student extends Person{

    public Student() {        
    }
    public Student(long id) {
        super(id);
    }

    @OneToMany(mappedBy="student")
    private List<AcademicWork> academicWorks;

    @NotNull(message="O curso do aluno é obrigatório")
    @Size(max=50, message="O curso do aluno deve ter no máximo 50 caracteres")
    @Column(nullable=false, length=50)
    private String course;

    @NotNull(message="O período do aluno é obrigatório")
    @Column(nullable=false)
    private int semester;
    
    public List<AcademicWork> getAcademicWorks() {
        return academicWorks;
    }
    public void setAcademicWorks(List<AcademicWork> academicWorks) {
        this.academicWorks = academicWorks;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public int getSemester() {
        return semester;
    }
    public void setSemester(int semester) {
        this.semester = semester;
    }    
}
