package com.GerenciadorTCC.controller.representationModel;

import org.springframework.hateoas.EntityModel;
import org.springframework.lang.NonNull;

import com.GerenciadorTCC.entities.AcademicWork;

public class AcademicWorkModel extends EntityModel<AcademicWork> {
    public AcademicWorkModel(@NonNull AcademicWork academicWork) {
        super(academicWork);
    }
}