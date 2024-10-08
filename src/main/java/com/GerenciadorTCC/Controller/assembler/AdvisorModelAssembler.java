package com.GerenciadorTCC.controller.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.GerenciadorTCC.controller.apirest.PersonApiRestController;
import com.GerenciadorTCC.controller.representationModel.AdvisorModel;
import com.GerenciadorTCC.entities.Advisor;

@Component
public class AdvisorModelAssembler extends RepresentationModelAssemblerSupport<Advisor, AdvisorModel> {

    public AdvisorModelAssembler() {
        super(PersonApiRestController.class, AdvisorModel.class);
    }

    @Override
    @NonNull
    public AdvisorModel toModel(@NonNull Advisor advisor) {
        try {
            AdvisorModel model = new AdvisorModel(advisor);
            model.add(linkTo(methodOn(PersonApiRestController.class).findByName(advisor.getName())).withSelfRel());
            model.add(linkTo(methodOn(PersonApiRestController.class).findById(advisor.getId())).withRel("findById"));
            model.add(linkTo(methodOn(PersonApiRestController.class).deleteById(advisor.getId())).withRel("delete"));
            model.add(linkTo(methodOn(PersonApiRestController.class).updateAdvisor(advisor)).withRel("update"));
            model.add(linkTo(methodOn(PersonApiRestController.class).createAdvisor(advisor)).withRel("create"));
            return model;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
