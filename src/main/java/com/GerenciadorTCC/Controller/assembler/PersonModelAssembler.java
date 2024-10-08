package com.GerenciadorTCC.controller.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.GerenciadorTCC.controller.apirest.PersonApiRestController;
import com.GerenciadorTCC.controller.representationModel.PersonModel;
import com.GerenciadorTCC.entities.Person;

@Component
public class PersonModelAssembler extends RepresentationModelAssemblerSupport<Person, PersonModel> {

    public PersonModelAssembler() {
        super(PersonApiRestController.class, PersonModel.class);
    }

    @Override
    @NonNull
    public PersonModel toModel(@NonNull Person person) {
        try {
            PersonModel model = new PersonModel(person);
            model.add(linkTo(methodOn(PersonApiRestController.class).findByName(person.getName())).withSelfRel());
            model.add(linkTo(methodOn(PersonApiRestController.class).findById(person.getId())).withRel("findById"));
            model.add(linkTo(methodOn(PersonApiRestController.class).deleteById(person.getId())).withRel("delete"));
            return model;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
