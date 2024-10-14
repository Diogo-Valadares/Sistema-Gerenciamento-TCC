package com.GerenciadorTCC.controller.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.GerenciadorTCC.controller.apirest.TaskApiRestController;
import com.GerenciadorTCC.controller.representationModel.TaskModel;
import com.GerenciadorTCC.entities.Task;

@Component
public class TaskModelAssembler extends RepresentationModelAssemblerSupport<Task, TaskModel> {

    public TaskModelAssembler() {
        super(TaskApiRestController.class, TaskModel.class);
    }

    @Override
    @NonNull
    public TaskModel toModel(@NonNull Task task) {
        try {
            TaskModel model = new TaskModel(task);
            model.add(linkTo(methodOn(TaskApiRestController.class).findByTitle(task.getTitle())).withSelfRel());
            model.add(linkTo(methodOn(TaskApiRestController.class).findById(task.getId())).withRel("findById"));
            model.add(linkTo(methodOn(TaskApiRestController.class).deleteById(task.getId())).withRel("delete"));
            model.add(linkTo(methodOn(TaskApiRestController.class).updateTask(task.getId(),task)).withRel("update"));
            model.add(linkTo(methodOn(TaskApiRestController.class).createTask(task.getId(),task)).withRel("create"));
            return model;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
