package com.GerenciadorTCC.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
public class Document implements Serializable {

    public Document() {
        this.taskDelivers = new ArrayList<>();
    }

    public Document(long id) {
        this.id = id;
        this.taskDelivers = new ArrayList<>();
    }
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message="O título do documento é obrigatório")
    @Size(max=50, message="O título do documento deve ter no máximo 50 caracteres")
    @Column(nullable=false, length=50)
    private String title;
    
    @NotBlank(message="O conteúdo do documento é obrigatório")
    @Size(max=10000, message="O conteúdo do documento deve ter no máximo 10000 caracteres")
    @Column(nullable=false, length=10000)
    private String content;

    @NotBlank(message="A citação do documento é obrigatória")
    @Size(max=500, message="A citação do documento deve ter no máximo 500 caracteres")
    @Column(nullable=false, length=500)
    private String citation;

    @NotNull(message="A data de upload do documento é obrigatória")
    @PastOrPresent(message="A data de upload do documento deve ser passada ou presente")
    @Column(nullable=false)
    private LocalDate uploadDate;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_avaliation")
    private Avaliation avaliation;

    @ManyToOne
    @JoinColumn(name = "fk_academicWork")
    private AcademicWork academicWork;

    @OneToMany(mappedBy="document", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskDeliver> taskDelivers;

    public List<TaskDeliver> getTaskDelivers() {
        return taskDelivers;
    }
    public void setTaskDelivers(List<TaskDeliver> taskDelivers) {
        if (this.taskDelivers != null) {
            this.taskDelivers.clear();
            if (taskDelivers != null) {
                this.taskDelivers.addAll(taskDelivers);
            }
        } else {
            this.taskDelivers = taskDelivers;
        }
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

    public Object findById(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
