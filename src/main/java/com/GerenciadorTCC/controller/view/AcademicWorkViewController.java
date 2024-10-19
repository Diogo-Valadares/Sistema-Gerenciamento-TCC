package com.GerenciadorTCC.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.GerenciadorTCC.controller.assembler.AcademicWorkModelAssembler;
import com.GerenciadorTCC.entities.AcademicWork;
import com.GerenciadorTCC.entities.Advisor;
import com.GerenciadorTCC.entities.Student;
import com.GerenciadorTCC.service.AcademicWorkService;
import com.GerenciadorTCC.service.PersonService;
import com.GerenciadorTCC.service.WorkTypeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/academicWorks")
public class AcademicWorkViewController {

    @Autowired
    private AcademicWorkService academicWorkService;

    @Autowired
    private PersonService personService;

    @Autowired
    private WorkTypeService workTypeService;

    @Autowired
    private AcademicWorkModelAssembler academicWorkModelAssembler;

    @GetMapping("/title/{title}")
    public String findByTitle(@PathVariable String title, Model model) {
        var academicWork = academicWorkService.findByTitle(title);
        if (academicWork.isPresent()) {
            model.addAttribute("academicWork", academicWorkModelAssembler.toModel(academicWork.get()));
            return "academicWorkView";
        } else {
            return "academicWorkNotFound";
        }
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable long id, Model model) {
        var academicWork = academicWorkService.findById(id);
        if (academicWork.isPresent()) {
            model.addAttribute("academicWork", academicWorkModelAssembler.toModel(academicWork.get()));
            return "academicWorkView";
        } else {
            return "academicWorkNotFound";
        }
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("academicWork", new AcademicWork());
        model.addAttribute("workTypes", workTypeService.findAll());
        model.addAttribute("students", personService.findAllByType(Student.class));
        model.addAttribute("advisors", personService.findAllByType(Advisor.class));
        return "academicWorkForm";
    }

    @GetMapping
    public String listAcademicWorks(Model model) {
        var academicWorks = academicWorkService.findAll();
        model.addAttribute("academicWorks", academicWorks);
        return "academicWorkList";
    }

    @PostMapping
    public String createAcademicWork(@Valid @ModelAttribute AcademicWork academicWork, BindingResult result, Model model) {
        if (result.hasErrors()) {            
            model.addAttribute("workTypes", workTypeService.findAll());
            model.addAttribute("students", personService.findAllByType(Student.class));
            model.addAttribute("advisors", personService.findAllByType(Advisor.class));
            model.addAttribute("academicWork", academicWork);
            return "academicWorkForm";
        }
        academicWorkService.save(academicWork);
        return "redirect:/academicWorks";
    }

    @PostMapping("/update/{id}")
    public String updateAcademicWork(@PathVariable long id, @Valid @ModelAttribute AcademicWork academicWork, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("academicWork", academicWork);
            model.addAttribute("workTypes", workTypeService.findAll());
            model.addAttribute("students", personService.findAllByType(Student.class));
            model.addAttribute("advisors", personService.findAllByType(Advisor.class));
            return "academicWorkForm";
        }
        academicWorkService.update(academicWork);
        return "redirect:/academicWorks";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable long id, Model model) {
        var academicWork = academicWorkService.findById(id);
        if (academicWork.isPresent()) {            
            model.addAttribute("workTypes", workTypeService.findAll());
            model.addAttribute("students", personService.findAllByType(Student.class));
            model.addAttribute("advisors", personService.findAllByType(Advisor.class));
            model.addAttribute("academicWork", academicWork.get());
            return "academicWorkForm";
        } else {
            return "academicWorkNotFound";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id) {
        academicWorkService.deleteById(id);
        return "redirect:/academicWorks";
    }
}
