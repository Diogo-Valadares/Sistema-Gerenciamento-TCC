package com.GerenciadorTCC.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
public class TaskDeliver implements Serializable {

    public TaskDeliver() {
    }
    public TaskDeliver(long id) {
        this.id = id;
    }
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "fk_document", nullable=false)
    private Document document;
    
    @ManyToOne
    @JoinColumn(name = "fk_task", nullable=false)
    private Task task;

    @NotNull(message = "A data de entrega da tarefa é obrigatória")
    @PastOrPresent(message = "A data de entrega da tarefa deve ser passada ou presente")
    @Column(nullable = false)
    private LocalDate deliverDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(LocalDate deliverDate) {
        this.deliverDate = deliverDate;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public Document getDocument() {
        return document;
    }
    public void setDocument(Document document) {
        this.document = document;
    }
    public Task getTask() {
        return task;
    }
    public void setTask(Task task) {
        this.task = task;
    }
}
