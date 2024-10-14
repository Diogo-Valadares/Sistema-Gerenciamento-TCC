package com.GerenciadorTCC.controller.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.GerenciadorTCC.controller.apirest.WorkTypeApiRestController;
import com.GerenciadorTCC.controller.representationModel.WorkTypeModel;
import com.GerenciadorTCC.entities.WorkType;

@Component
public class WorkTypeModelAssembler extends RepresentationModelAssemblerSupport<WorkType, WorkTypeModel> {

    public WorkTypeModelAssembler() {
        super(WorkTypeApiRestController.class, WorkTypeModel.class);
    }

    @Override
    @NonNull
    public WorkTypeModel toModel(@NonNull WorkType workType) {
        try {
            WorkTypeModel model = new WorkTypeModel(workType);
            model.add(linkTo(methodOn(WorkTypeApiRestController.class).findByName(workType.getName())).withSelfRel());
            model.add(linkTo(methodOn(WorkTypeApiRestController.class).findById(workType.getId())).withRel("findById"));
            model.add(linkTo(methodOn(WorkTypeApiRestController.class).updateWorkType(workType.getId(), workType)).withRel("update"));
            return model;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
