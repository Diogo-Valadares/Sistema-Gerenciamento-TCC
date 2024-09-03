package com.GerenciadorTCC.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Student extends Person{

    public Student() {        
    }
    public Student(long id) {
        super(id);
    }

    @Column(nullable=false, length=50)
    private String course;
    @Column(nullable=false)
    private int courseYear;
    
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public int getCourseYear() {
        return courseYear;
    }
    public void setCourseYear(int year) {
        this.courseYear = year;
    }    
}
