package com.GerenciadorTCC.controller.representationModel;

import org.springframework.hateoas.EntityModel;
import org.springframework.lang.NonNull;

import com.GerenciadorTCC.entities.Document;

public class DocumentModel extends EntityModel<Document> {
    public DocumentModel(@NonNull Document document) {
        super(document);
    }
}