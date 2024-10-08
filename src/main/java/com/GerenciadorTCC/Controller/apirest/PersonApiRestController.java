package com.GerenciadorTCC.controller.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GerenciadorTCC.controller.assembler.AdvisorModelAssembler;
import com.GerenciadorTCC.controller.assembler.PersonModelAssembler;
import com.GerenciadorTCC.controller.assembler.StudentModelAssembler;
import com.GerenciadorTCC.controller.representationModel.AdvisorModel;
import com.GerenciadorTCC.controller.representationModel.PersonModel;
import com.GerenciadorTCC.controller.representationModel.StudentModel;
import com.GerenciadorTCC.entities.Advisor;
import com.GerenciadorTCC.entities.Person;
import com.GerenciadorTCC.entities.Student;
import com.GerenciadorTCC.service.PersonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "api/person")
public class PersonApiRestController {

    private StudentModelAssembler studentModelAssembler;    
    @Autowired
    private PersonModelAssembler personModelAssembler;    
    @Autowired
    private AdvisorModelAssembler advisorModelAssembler; 
    @Autowired
    private PersonService personService;
    
    @Operation(summary = "Achar uma pessoa pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pessoa encontrada", 
            content = {@Content(mediaType = "application/json", 
                schema = @Schema(implementation = Person.class))}
        ),
        @ApiResponse(responseCode = "404", description = "Pessoa não encontrada", 
        content = @Content
        )
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<PersonModel> findById(@Parameter(description = "ID da pessoa a ser achada") 
    @PathVariable long id) throws Exception{       
        var pessoa = personService.findById(id);
        return pessoa.map(person -> ResponseEntity.ok(personModelAssembler.toModel(person)))
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Achar uma pessoa pelo nome")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pessoa encontrada", 
            content = {@Content(mediaType = "application/json", 
                schema = @Schema(implementation = Person.class))}
        ),
        @ApiResponse(responseCode = "404", description = "Pessoa não encontrada", 
        content = @Content
        )
    })
    @GetMapping("/{name}")
    public ResponseEntity<PersonModel> findByName(@Parameter(description = "Nome da pessoa a ser achada") 
    @PathVariable String name) throws Exception{         
        var pessoa = personService.findByName(name);
        return pessoa.map(person -> ResponseEntity.ok(personModelAssembler.toModel(person)))
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Apagar uma pessoa pelo  ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pessoa deletada", 
            content = @Content
        ),
        @ApiResponse(responseCode = "403", description = "Sem privilégios para apagar pessoa", 
        content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Pessoa não encontrada", 
        content = @Content
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@Parameter(description = "Nome da pessoa a ser achada") 
    @PathVariable long id) throws Exception{
        var pessoa = personService.findById(id);
        if (pessoa.isPresent()) {
            personService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Atualizar um estudante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Estudante atualizado", 
            content = @Content
        ),
        @ApiResponse(responseCode = "403", description = "Sem privilégios para atualizar estudante", 
        content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Estudante não encontrado", 
        content = @Content
        )
    })        
    @PutMapping("/student/{student}")
    public ResponseEntity<StudentModel> updateStudent(@Parameter(description = "Objeto do estudante sendo atualizado") 
    @PathVariable Student student) throws Exception{
        var updatedStudent = personService.updateStudent(student);
        return ResponseEntity.ok(studentModelAssembler.toModel(updatedStudent));
    }  

    @Operation(summary = "criar um estudante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Estudante criado", 
            content = @Content
        ),
        @ApiResponse(responseCode = "403", description = "Sem privilégios para criar estudante", 
        content = @Content
        )
    })    
    @PostMapping("/student/{student}")
    public ResponseEntity<StudentModel> createStudent(@Parameter(description = "Objeto do estudante sendo criado") 
    @PathVariable Student student) throws Exception{
        var createdStudent = personService.createStudent(student);
        return ResponseEntity.ok(studentModelAssembler.toModel(createdStudent));
    }

    @Operation(summary = "Atualizar um orientador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Orientador atualizado", 
            content = @Content
        ),
        @ApiResponse(responseCode = "403", description = "Sem privilégios para atualizar orientador", 
        content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Orientador não encontrado", 
        content = @Content
        )
    })
    @PutMapping("/advisor/{advisor}")
    public ResponseEntity<AdvisorModel> updateAdvisor(@Parameter(description = "Objeto do orientador sendo atualizado")
    @PathVariable Advisor advisor) throws Exception{
        var updatedAdvisor = personService.updateAdvisor(advisor);
        return ResponseEntity.ok(advisorModelAssembler.toModel(updatedAdvisor));
    }

    @Operation(summary = "criar um orientador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Orientador criado", 
            content = @Content
        ),
        @ApiResponse(responseCode = "403", description = "Sem privilégios para criar orientador", 
        content = @Content
        )
    })
    @PostMapping("/advisor/{advisor}")
    public ResponseEntity<AdvisorModel> createAdvisor(@Parameter(description = "Objeto do orientador sendo criado")
    @PathVariable Advisor advisor) throws Exception{
        var createdAdvisor = personService.createAdvisor(advisor);
        return ResponseEntity.ok(advisorModelAssembler.toModel(createdAdvisor));
    }
}
