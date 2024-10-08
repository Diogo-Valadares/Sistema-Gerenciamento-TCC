package com.GerenciadorTCC.controller.representationModel;

import org.springframework.hateoas.EntityModel;
import org.springframework.lang.NonNull;

import com.GerenciadorTCC.entities.Advisor;

public class AdvisorModel extends EntityModel<Advisor> {
    public AdvisorModel(@NonNull Advisor advisor) {
        super(advisor);
    }
}