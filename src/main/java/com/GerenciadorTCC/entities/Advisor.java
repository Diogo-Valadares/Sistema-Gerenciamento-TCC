package com.GerenciadorTCC.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Advisor extends Person{

    public Advisor() {        
    }    
    public Advisor(long id) {
        super(id);
    }

    @Column(nullable=false, length=50)
    private String speciality;
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
