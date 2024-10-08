package com.GerenciadorTCC.controller.representationModel;

import org.springframework.hateoas.EntityModel;
import org.springframework.lang.NonNull;

import com.GerenciadorTCC.entities.Person;

public class PersonModel extends EntityModel<Person> {
    public PersonModel(@NonNull Person person) {
        super(person);
    }
}