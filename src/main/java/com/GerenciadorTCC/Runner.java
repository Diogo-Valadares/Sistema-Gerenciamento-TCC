package com.GerenciadorTCC;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.GerenciadorTCC.entities.AcademicWork;
import com.GerenciadorTCC.entities.Advisor;
import com.GerenciadorTCC.entities.Avaliation;
import com.GerenciadorTCC.entities.Document;
import com.GerenciadorTCC.entities.Student;
import com.GerenciadorTCC.entities.Task;
import com.GerenciadorTCC.entities.TaskDeliver;
import com.GerenciadorTCC.entities.TaskStatus;
import com.GerenciadorTCC.entities.WorkType;
import com.GerenciadorTCC.service.AcademicWorkService;
import com.GerenciadorTCC.service.AvaliationService;
import com.GerenciadorTCC.service.DocumentService;
import com.GerenciadorTCC.service.PersonService;
import com.GerenciadorTCC.service.TaskDeliverService;
import com.GerenciadorTCC.service.TaskService;
import com.GerenciadorTCC.service.WorkTypeService;

@Component
public class Runner implements CommandLineRunner{
 
    @Autowired
    PersonService personService;
    @Autowired
    AcademicWorkService academicWorkService;
    @Autowired
    AvaliationService avaliationService;
    @Autowired
    DocumentService documentService;    
    @Autowired
    TaskService taskService;
    @Autowired
    TaskDeliverService taskDeliverService;
    @Autowired
    WorkTypeService workTypeService;

    @Override
    public void run(String... args) throws Exception {
        var advisorTest = new Advisor();
        advisorTest.setName("João");
        advisorTest.setEmail("joao@gmail.com");
        advisorTest.setCpf("61500579092");
        advisorTest.setRg("12.345.678-81");
        advisorTest.setPhone("(61)50057-9092");
        advisorTest.setAddress("Rua 1");
        advisorTest.setBirthdate(LocalDate.of(2000, 1, 1));
        advisorTest.setPassword("634vS4@4");
        advisorTest.setSpeciality("física");
        advisorTest.setDisponibility("todos dias das 8h às 12h");

        var studentTest = new Student();
        studentTest.setName("Maria");
        studentTest.setEmail("maria@testemail.com");
        studentTest.setCpf("38330603016");
        studentTest.setRg("12.345.678-90");
        studentTest.setPhone("(54)33353-9092");
        studentTest.setAddress("Rua 1");
        studentTest.setBirthdate(LocalDate.of(2000, 1, 1));
        studentTest.setPassword("634vS4@4");
        studentTest.setCourse("Engenharia de Software");
        studentTest.setSemester(1);        

        personService.save(advisorTest);
        personService.save(studentTest);

        var workTypeTest = new WorkType();
        workTypeTest.setName("Artigo");
        workTypeTest.setDescription("Artigo científico com revisão bibliográfica e pesquisa de campo");
        workTypeService.save(workTypeTest);

        var academicWorkTest = new AcademicWork();
        academicWorkTest.setTitle("Título do Trabalho");
        academicWorkTest.setAdvisor(advisorTest);
        academicWorkTest.setStudent(studentTest);
        academicWorkTest.setWorkType(workTypeTest);
        academicWorkTest.setEndDate(LocalDate.of(9999, 12, 31));
        academicWorkService.save(academicWorkTest);
        
        var taskTest = new Task();
        taskTest.setAcademicWork(academicWorkTest);
        taskTest.setTitle("Título da tarefa");
        taskTest.setDescription("Descrição da tarefa");
        taskTest.setDeadline(LocalDate.of(9999, 12, 31));
        taskTest.setStatus(TaskStatus.PENDING);
        taskService.save(taskTest);

        var documentTest = new Document();
        documentTest.setAcademicWork(academicWorkTest);
        documentTest.setContent("content234234");
        documentTest.setTitle("titulo do documento");
        documentTest.setCitation("Pessoa, titulo do Documento, 2021");
        documentTest.setUploadDate(LocalDate.now());
        documentService.save(documentTest);  

        var taskDeliverTest = new TaskDeliver(); 
        taskDeliverTest.setTask(taskTest);
        taskDeliverTest.setDeliverDate(LocalDate.now());
        taskDeliverTest.setDocument(documentTest);
        taskDeliverService.save(taskDeliverTest);

        var avaliationTest = new Avaliation();
        avaliationTest.setDocument(documentTest);
        avaliationTest.setDate(LocalDate.now());
        avaliationTest.setAnnotation("muito bom mas muda tal negócio");        
        avaliationService.save(avaliationTest);

        documentTest.setAvaliation(avaliationTest);
        documentService.save(documentTest);
    }
}