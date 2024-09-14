package com.GerenciadorTCC.entities;

public enum TaskStatus {

    PENDING("Pendente"),
    IN_PROGRESS("Em andamento"),    
    IN_REVIEW("Em revisão"),
    CHANGES_REQUIRED("Correções necessárias"),
    DONE("Concluída");

    private final String description;

    TaskStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
