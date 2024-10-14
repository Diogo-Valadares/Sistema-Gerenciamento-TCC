package com.GerenciadorTCC.controller.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.GerenciadorTCC.controller.apirest.AcademicWorkApiRestController;
import com.GerenciadorTCC.controller.representationModel.AcademicWorkModel;
import com.GerenciadorTCC.entities.AcademicWork;

@Component
public class AcademicWorkModelAssembler extends RepresentationModelAssemblerSupport<AcademicWork, AcademicWorkModel> {

    public AcademicWorkModelAssembler() {
        super(AcademicWorkApiRestController.class, AcademicWorkModel.class);
    }

    @Override
    @NonNull
    public AcademicWorkModel toModel(@NonNull AcademicWork academicWork) {
        try {
            AcademicWorkModel model = new AcademicWorkModel(academicWork);
            model.add(linkTo(methodOn(AcademicWorkApiRestController.class).findByTitle(academicWork.getTitle())).withSelfRel());
            model.add(linkTo(methodOn(AcademicWorkApiRestController.class).findByID(academicWork.getId())).withRel("findById"));
            model.add(linkTo(methodOn(AcademicWorkApiRestController.class).deleteById(academicWork.getId())).withRel("delete"));
            model.add(linkTo(methodOn(AcademicWorkApiRestController.class).updateAcademicWork(academicWork)).withRel("update"));
            model.add(linkTo(methodOn(AcademicWorkApiRestController.class).createAcademicWork(academicWork)).withRel("create"));
            return model;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
