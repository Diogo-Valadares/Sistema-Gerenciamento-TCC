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
import org.springframework.web.bind.annotation.RequestParam;

import com.GerenciadorTCC.controller.assembler.TaskModelAssembler;
import com.GerenciadorTCC.entities.Task;
import com.GerenciadorTCC.service.AcademicWorkService;
import com.GerenciadorTCC.service.TaskService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/tasks")
public class TaskViewController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private AcademicWorkService academicworkService;
    
    @Autowired
    private TaskModelAssembler taskModelAssembler;

    @GetMapping("/title/{title}")
    public String findByTitle(@PathVariable String title, Model model) {
        var task = taskService.findByTitle(title);
        if (task.isPresent()) {
            model.addAttribute("task", taskModelAssembler.toModel(task.get()));
            return "taskView";
        } else {
            return "taskNotFound";
        }
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable long id, Model model) {
        var task = taskService.findById(id);
        if (task.isPresent()) {
            model.addAttribute("task", taskModelAssembler.toModel(task.get()));
            return "taskView";
        } else {
            return "taskNotFound";
        }
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("academicWorks", academicworkService.findAll());
        return "taskForm";
    }

    @GetMapping
    public String listTasks(Model model) {
        var tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "taskList";
    }

    @PostMapping
    public String createTask(@Valid @ModelAttribute Task task, BindingResult result, @RequestParam Long academicWorkId, Model model) {
        if (result.hasErrors()) {            
            model.addAttribute("academicWorks", academicworkService.findAll());
            model.addAttribute("task", task);
            return "taskForm";
        }
        var academicWork = academicworkService.findById(academicWorkId);
        if (academicWork.isPresent()) {
            task.setAcademicWork(academicWork.get());
        }
        taskService.save(task);
        return "redirect:/tasks";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable long id, @Valid @ModelAttribute Task task, BindingResult result, @RequestParam Long academicWorkId, Model model) {
        if (result.hasErrors()) {            
            model.addAttribute("academicWorks", academicworkService.findAll());
            model.addAttribute("task", task);
            return "taskForm";
        }
        var academicWork = academicworkService.findById(academicWorkId);
        if (academicWork.isPresent()) {
            task.setAcademicWork(academicWork.get());
        }
        taskService.update(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable long id, Model model) {
        var task = taskService.findById(id);
        if (task.isPresent()) {            
            model.addAttribute("academicWorks", academicworkService.findAll());
            model.addAttribute("task", task.get());
            return "taskForm";
        } else {
            return "taskNotFound";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id) {
        taskService.deleteById(id);
        return "redirect:/tasks";
    }
}
