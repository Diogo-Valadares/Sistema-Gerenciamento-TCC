package com.GerenciadorTCC.controller.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.GerenciadorTCC.controller.apirest.DocumentApiRestController;
import com.GerenciadorTCC.controller.representationModel.DocumentModel;
import com.GerenciadorTCC.entities.Document;

@Component
public class DocumentModelAssembler extends RepresentationModelAssemblerSupport<Document, DocumentModel> {

    public DocumentModelAssembler() {
        super(DocumentApiRestController.class, DocumentModel.class);
    }

    @Override
    @NonNull
    public DocumentModel toModel(@NonNull Document document) {
        try {
            DocumentModel model = new DocumentModel(document);
            model.add(linkTo(methodOn(DocumentApiRestController.class).findByTitle(document.getTitle())).withSelfRel());
            model.add(linkTo(methodOn(DocumentApiRestController.class).findById(document.getId())).withRel("findById"));
            model.add(linkTo(methodOn(DocumentApiRestController.class).deleteById(document.getId())).withRel("delete"));
            model.add(linkTo(methodOn(DocumentApiRestController.class).updateDocument(document.getId(),document)).withRel("update"));
            model.add(linkTo(methodOn(DocumentApiRestController.class).createDocument(document.getId(),document)).withRel("create"));
            model.add(linkTo(methodOn(DocumentApiRestController.class).addAvaliation(document.getId(), document.getAvaliation())).withRel("addAvaliation"));
            return model;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
