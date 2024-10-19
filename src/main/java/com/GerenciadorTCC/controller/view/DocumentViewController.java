package com.GerenciadorTCC.controller.view;

import java.time.LocalDate;

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

import com.GerenciadorTCC.controller.assembler.DocumentModelAssembler;
import com.GerenciadorTCC.entities.AcademicWork;
import com.GerenciadorTCC.entities.Avaliation;
import com.GerenciadorTCC.entities.Document;
import com.GerenciadorTCC.entities.Task;
import com.GerenciadorTCC.entities.TaskDeliver;
import com.GerenciadorTCC.service.AcademicWorkService;
import com.GerenciadorTCC.service.AvaliationService;
import com.GerenciadorTCC.service.DocumentService;
import com.GerenciadorTCC.service.TaskDeliverService;
import com.GerenciadorTCC.service.TaskService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/documents")
public class DocumentViewController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private AvaliationService avaliationService;

    @Autowired
    private TaskDeliverService taskDeliverService;

    @Autowired
    private AcademicWorkService academicworkService;

    @Autowired
    private DocumentModelAssembler documentModelAssembler;

    @GetMapping("/title/{title}")
    public String findByTitle(@PathVariable String title, Model model) {
        var document = documentService.findByTitle(title);
        if (document.isPresent()) {
            model.addAttribute("document", documentModelAssembler.toModel(document.get()));
            return "documentView";
        } else {
            return "documentNotFound";
        }
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable long id, Model model) {
        var document = documentService.findById(id);
        if (document.isPresent()) {
            model.addAttribute("document", documentModelAssembler.toModel(document.get()));
            return "documentView";
        } else {
            return "documentNotFound";
        }
    }

    @GetMapping
    public String listDocuments(Model model) {
        var documents = documentService.findAll();
        model.addAttribute("documents", documents);
        model.addAttribute("tasks",taskService.findAll());
        return "documentList";
        }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("document", new Document());
        model.addAttribute("task", new Task());
        model.addAttribute("tasks",taskService.findAll());
        model.addAttribute("academicWorks", academicworkService.findAll());
        return "documentForm";
    }
    
    @PostMapping
    public String createDocument(@Valid @ModelAttribute Document document, BindingResult result, @RequestParam Long taskId, @RequestParam Long academicWorkId, Model model) {
        if (result.hasErrors()) {            
            model.addAttribute("academicWorks", academicworkService.findAll());
            model.addAttribute("document", document);
            model.addAttribute("tasks", taskService.findAll());
            return "documentForm";
        }

        Task task = taskService.findById(taskId).get();
        AcademicWork academicWork = academicworkService.findById(academicWorkId).get();
        document.setAcademicWork(academicWork);
        documentService.save(document);

        TaskDeliver taskDeliver = new TaskDeliver();
        taskDeliver.setDocument(document);
        taskDeliver.setTask(task);
        taskDeliver.setDeliverDate(LocalDate.now());
        taskDeliverService.save(taskDeliver);

        return "redirect:/documents";
    }

    @PostMapping("/update/{id}")
    public String updateDocument(@PathVariable long id, @Valid @ModelAttribute Document document, BindingResult result, @RequestParam Long taskId, @RequestParam Long academicWorkId, Model model) {
        if (result.hasErrors()) {            
            model.addAttribute("academicWorks", academicworkService.findAll());
            model.addAttribute("document", document);
            return "documentForm";
        }

        AcademicWork academicWork = academicworkService.findById(academicWorkId).get();
        document.setAcademicWork(academicWork);
        documentService.update(document);
        return "redirect:/documents";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable long id, Model model) {
        var document = documentService.findById(id);
        var task = document.get().getTaskDelivers().get(0).getTask();
        if (document.isPresent()) {            
            model.addAttribute("academicWorks", academicworkService.findAll());
            model.addAttribute("document", document.get());
            model.addAttribute("task", task);
            model.addAttribute("tasks",taskService.findAll());
            return "documentForm";
        } else {
            return "documentNotFound";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id) {
        documentService.deleteById(id);
        return "redirect:/documents";
    }

    @GetMapping("/avaliation/{id}")
    public String viewAvaliation(@PathVariable long id, Model model) {
        var document = documentService.findById(id);
        if (document.isPresent()) {
            model.addAttribute("avaliation", document.get().getAvaliation());
            model.addAttribute("document", document.get());
            return "avaliationView";
        } else {
            return "documentNotFound";
        }
    }

    @GetMapping("/evaluate/{id}")
    public String showEvaluateForm(@PathVariable long id, Model model) {
        var document = documentService.findById(id);
        if (document.isPresent()) {
            var newAvaliation = new Avaliation();            
            newAvaliation.setId(id);
            model.addAttribute("avaliation", newAvaliation);
            model.addAttribute("document", document.get());
            return "evaluateForm";
        } else {
            return "documentNotFound";
        }
    }

    @PostMapping("/evaluate/{id}")
    public String evaluateDocument(@PathVariable long id, @Valid @ModelAttribute Avaliation avaliation, BindingResult result, Model model) {
        var document = documentService.findById(id);
        if (document.isPresent()) {
            if (result.hasErrors()) {
                model.addAttribute("avaliation", avaliation);
                model.addAttribute("document", document.get());
                return "evaluateForm";
            }            
            
            avaliationService.save(avaliation);
            document.get().setAvaliation(avaliation);
            documentService.save(document.get());
            
            return "redirect:/documents";
        } else {
            return "documentNotFound";
        }
    }
}
