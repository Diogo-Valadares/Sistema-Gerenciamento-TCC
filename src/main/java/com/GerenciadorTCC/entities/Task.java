package com.GerenciadorTCC.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

    @NotBlank(message="O título da tarefa é obrigatório")
    @Max(value=100, message="O título da tarefa deve ter no máximo 100 caracteres")
    @Column(nullable = false,length=100)
    private String title;

    @Enumerated(EnumType.STRING)
    @NotNull(message="O status da tarefa é obrigatório")
    @Column(nullable = false)
    private TaskStatus status;

    @NotBlank(message="A descrição da tarefa é obrigatória")
    @Max(value=2500, message="A descrição da tarefa deve ter no máximo 2500 caracteres")
    @Column(nullable = false, length = 2500)
    private String description;

    @NotNull(message="A data limite da tarefa é obrigatória")
    @Future(message="A data limite da tarefa deve ser futura")
    @Column(nullable = false)
    private LocalDate deadline;

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
    public TaskStatus getStatus() {
        return status;
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
    public List<TaskDeliver> getTaskDelivers() {
        return taskDelivers;
    }
    public void setTaskDelivers(List<TaskDeliver> taskDelivers) {
        this.taskDelivers = taskDelivers;
    }
    
}
