package com.GerenciadorTCC.controller.representationModel;

import org.springframework.hateoas.EntityModel;
import org.springframework.lang.NonNull;

import com.GerenciadorTCC.entities.WorkType;

public class WorkTypeModel extends EntityModel<WorkType> {
    public WorkTypeModel(@NonNull WorkType workType) {
        super(workType);
    }
}