package com.GerenciadorTCC.controller.apirest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GerenciadorTCC.entities.Person;
import com.GerenciadorTCC.entities.Student;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "api/person")
public class PersonApiRestController {

    @Autowired
    private StudentModelAssembler studentAssembler;

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
    public Person findByName(@Parameter(description = "Nome da pessoa a ser achada") 
    @PathVariable String name) throws Exception{
        throw new Exception("not implemented");
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
    public void deleteById(@Parameter(description = "Nome da pessoa a ser achada") 
    @PathVariable long id) throws Exception{
        throw new Exception("not implemented");
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
    public void updateStudent(@Parameter(description = "Objeto do estudante sendo atualizado") 
    @PathVariable Student student) throws Exception{
        throw new Exception("not implemented");
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
    public void createStudent(@Parameter(description = "Objeto do estudante sendo criado") 
    @PathVariable Student student) throws Exception{
        throw new Exception("not implemented");
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
    public void updateAdvisor(@Parameter(description = "Objeto do orientador sendo atualizado")
    @PathVariable Person advisor) throws Exception{
        throw new Exception("not implemented");
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
    public void createAdvisor(@Parameter(description = "Objeto do orientador sendo criado")
    @PathVariable Person advisor) throws Exception{
        throw new Exception("not implemented");
    }
}
