package com.GerenciadorTCC.controller.view;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.GerenciadorTCC.controller.assembler.PersonModelAssembler;
import com.GerenciadorTCC.entities.Advisor;
import com.GerenciadorTCC.entities.Person;
import com.GerenciadorTCC.entities.Student;
import com.GerenciadorTCC.service.PersonService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/people")
public class PersonViewController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonModelAssembler personModelAssembler;

    @GetMapping("/name/{name}")
    public String findByName(@PathVariable String name, Model model) {
        var person = personService.findByName(name);
        if (person.isPresent()) {
            model.addAttribute("person", personModelAssembler.toModel(person.get()));
            return "personView";
        } else {
            return "personNotFound";
        }
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable long id, Model model) {
        var person = personService.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", personModelAssembler.toModel(person.get()));
            return "personView";
        } else {
            return "personNotFound";
        }
    }

    @GetMapping("/new/student")
    public String showCreateStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "studentForm";
    }

    @GetMapping("/new/advisor")
    public String showCreateAdvisorForm(Model model) {
        model.addAttribute("advisor", new Advisor());
        return "advisorForm";
    }

    @GetMapping
    public String listPeople(Model model) {
        var people = personService.findAll();
        model.addAttribute("people", people);
        List<String> editUrls = people.stream()
        .map(person -> {
            if (person == null) {
                return "";
            }
            return person instanceof Student ? 
                "/people/edit/student/" + person.getId() : 
                "/people/edit/advisor/" + person.getId();
        })
        .collect(Collectors.toList());

        model.addAttribute("editUrls", editUrls);
        return "personList";
    }

    @PostMapping("/new/student")
    public String createStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("student", student);
            return "studentForm";
        }
        personService.save(student);
        return "redirect:/people";
    }

    @PostMapping("/new/advisor")
    public String createAdvisor(@Valid @ModelAttribute("advisor") Advisor advisor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("advisor", advisor);
            return "advisorForm";
        }
        personService.save(advisor);
        return "redirect:/people";
    }

    @PostMapping("/update/student/{id}")
    public String updateStudent(@PathVariable long id, @Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("student", student);
            return "studentForm";
        }
        personService.updateStudent(student);
        return "redirect:/people";
    }

    @PostMapping("/update/advisor/{id}")
    public String updateAdvisor(@PathVariable long id, @Valid @ModelAttribute("advisor") Advisor advisor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("advisor", advisor);
            return "advisorForm";
        }
        personService.updateAdvisor(advisor);
        return "redirect:/people";
    }

    @GetMapping("/edit/student/{id}")
    public String showUpdateStudentForm(@PathVariable long id, Model model) {
        var person = personService.findById(id);
        if (person.isPresent() && person.get() instanceof Student) {
            model.addAttribute("student", person.get());
            return "studentForm";
        } else {
            return "personNotFound";
        }
    }

    @GetMapping("/edit/advisor/{id}")
    public String showUpdateAdvisorForm(@PathVariable long id, Model model) {
        var person = personService.findById(id);
        if (person.isPresent() && person.get() instanceof Advisor) {
            model.addAttribute("advisor", person.get());
            return "advisorForm";
        } else {
            return "personNotFound";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id) {
        personService.deleteById(id);
        return "redirect:/people";
    }

    public String getEditUrl(Person person) {
        if (person instanceof Student) {
            return "/people/new/student/" + person.getId();
        } else {
            return "/people/new/advisor/" + person.getId();
        }
    }
}
