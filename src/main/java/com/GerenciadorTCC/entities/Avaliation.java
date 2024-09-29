package com.GerenciadorTCC.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
public class Avaliation implements Serializable {

    public Avaliation() {
    }
    public Avaliation(long id) {
        this.id = id;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "A anotação da avaliação é obrigatória")
    @Size(max = 5000, message = "A anotação da avaliação deve ter no máximo 5000 caracteres")
    @Column(nullable = false, length = 5000)
    private String annotation;
    
    @NotNull(message = "A data da avaliação é obrigatória")
    @PastOrPresent(message = "A data da avaliação deve ser passada ou presente")
    @Column(nullable = false)
    private LocalDate date;
    
    @OneToOne(mappedBy = "avaliation")
    private Document document;
    
    public Document getDocument() {
        return document;
    }
    public void setDocument(Document document) {
        this.document = document;
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
    public String getAnnotation() {
        return annotation;
    }
    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
