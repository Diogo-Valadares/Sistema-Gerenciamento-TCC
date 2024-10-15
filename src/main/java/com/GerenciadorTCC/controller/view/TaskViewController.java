package com.GerenciadorTCC.controller.view;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.GerenciadorTCC.controller.assembler.TaskModelAssembler;
import com.GerenciadorTCC.entities.Task;
import com.GerenciadorTCC.service.TaskService;
@Controller
@RequestMapping(path = "/tasks")
public class TaskViewController {

    @Autowired
    private TaskService taskService;

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

    @GetMapping
    public String listTasks(Model model) {
        var tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "taskList";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        return "taskForm";
    }
    @GetMapping("/test")
    public String teste() {
        return "home";
    }

    @PostMapping
    public String createTask(@ModelAttribute Task task) {
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable long id, Model model) {
        var task = taskService.findById(id);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            return "taskForm";
        } else {
            return "taskNotFound";
        }
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable long id, @ModelAttribute Task task) {
        taskService.update(task);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id) {
        taskService.deleteById(id);
        return "redirect:/tasks";
    }
}
