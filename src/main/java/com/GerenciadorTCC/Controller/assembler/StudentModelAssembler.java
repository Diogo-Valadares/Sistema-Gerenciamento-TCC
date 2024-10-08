package com.GerenciadorTCC.controller.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.GerenciadorTCC.controller.apirest.PersonApiRestController;
import com.GerenciadorTCC.controller.representationModel.StudentModel;
import com.GerenciadorTCC.entities.Student;

@Component
public class StudentModelAssembler extends RepresentationModelAssemblerSupport<Student, StudentModel> {

    public StudentModelAssembler() {
        super(PersonApiRestController.class, StudentModel.class);
    }

    @Override
    @NonNull
    public StudentModel toModel(@NonNull Student student) {
        try {
            StudentModel model = new StudentModel(student);
            model.add(linkTo(methodOn(PersonApiRestController.class).findByName(student.getName())).withSelfRel());
            model.add(linkTo(methodOn(PersonApiRestController.class).findById(student.getId())).withRel("findById"));
            model.add(linkTo(methodOn(PersonApiRestController.class).deleteById(student.getId())).withRel("delete"));
            model.add(linkTo(methodOn(PersonApiRestController.class).updateStudent(student)).withRel("update"));
            model.add(linkTo(methodOn(PersonApiRestController.class).createStudent(student)).withRel("create"));
            return model;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
