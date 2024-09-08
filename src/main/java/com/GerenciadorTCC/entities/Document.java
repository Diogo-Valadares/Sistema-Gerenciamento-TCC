package com.GerenciadorTCC.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Document implements Serializable {

    public Document() {
    }
    public Document(long id) {
        this.id = id;
    }
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable=false, length=50)
    private String title;
    
    @Column(nullable=false, length=10000)
    private String content;

    @Column(nullable=false, length=500)
    private String citation;

    @Column(nullable=false)
    private LocalDate uploadDate;
    
    @OneToOne
    @JoinColumn(name = "fk_avaliation")
    private Avaliation avaliation;

    @ManyToOne
    @JoinColumn(name = "fk_academicWork")
    private AcademicWork academicWork;

    @OneToMany(mappedBy="document")
    private List<TaskDeliver> taskDelivers;

    public List<TaskDeliver> getTaskDelivers() {
        return taskDelivers;
    }
    public void setTaskDelivers(List<TaskDeliver> taskDelivers) {
        this.taskDelivers = taskDelivers;
    }
    public Avaliation getAvaliation() {
        return avaliation;
    }
    public void setAvaliation(Avaliation avaliation) {
        this.avaliation = avaliation;
    }    
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
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getCitation() {
        return citation;
    }
    public void setCitation(String citation) {
        this.citation = citation;
    }
    public LocalDate getUploadDate() {
        return uploadDate;
    }
    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }
}
