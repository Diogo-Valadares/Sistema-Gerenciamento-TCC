package com.GerenciadorTCC.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

//@Entity
public class Student extends Person{

    public Student() {        
    }
    public Student(long id) {
        super(id);
    }

    @Column(nullable=false, length=50)
    private String course;
    @Column(nullable=false)
    private int year;
    
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }    
}
