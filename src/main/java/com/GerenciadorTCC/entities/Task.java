package com.GerenciadorTCC.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Task implements Serializable {

    public Task() {
    }
    public Task(long id) {
        this.id = id;
    }
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false,length=100)
    private String title;

    @Column(nullable = false,length=20)
    private String status;

    @Column(nullable = false, length = 2500)
    private String description;

    @Column(nullable = false)
    private Date deadline;

    @ManyToOne
    @JoinColumn(name = "fk_academicWork")
    private AcademicWork academicWork;
    
    @OneToMany(mappedBy = "task")
    private List<TaskDeliver> taskDelivers;

    public AcademicWork getAcademicWork() {
        return academicWork;
    }
    public void setAcademicWork(AcademicWork academicWork) {
        this.academicWork = academicWork;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    public List<TaskDeliver> getTaskDelivers() {
        return taskDelivers;
    }
    public void setTaskDelivers(List<TaskDeliver> taskDelivers) {
        this.taskDelivers = taskDelivers;
    }
    
}
