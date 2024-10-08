package com.GerenciadorTCC.controller.representationModel;

import org.springframework.hateoas.EntityModel;
import org.springframework.lang.NonNull;

import com.GerenciadorTCC.entities.Student;

public class StudentModel extends EntityModel<Student> {
    public StudentModel(@NonNull Student student) {
        super(student);
    }
}