package com.GerenciadorTCC.controller.representationModel;

import org.springframework.hateoas.EntityModel;
import org.springframework.lang.NonNull;

import com.GerenciadorTCC.entities.Task;

public class TaskModel extends EntityModel<Task> {
    public TaskModel(@NonNull Task task) {
        super(task);
    }
}