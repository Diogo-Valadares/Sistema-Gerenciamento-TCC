package com.GerenciadorTCC.controller.apirest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GerenciadorTCC.entities.AcademicWork;
import com.GerenciadorTCC.entities.Student;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = "api/academicWork")
public class AcademicWorkApiRestController {
    @Operation(summary = "Achar um trabalho por título")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Trabalho encontrado", 
            content = {@Content(mediaType = "application/json", 
                schema = @Schema(implementation = AcademicWork.class))}
        ),
        @ApiResponse(responseCode = "403", description = "Trabalho de outra pessoa, acesso proibído", 
            content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Trabalho não encontrado", 
            content = @Content
        )
    })
    @GetMapping("/{title}")
    public AcademicWork findByTitle(@Parameter(description = "Titulo do trabalho a ser encontrado") 
    @PathVariable String title) throws Exception{
        throw new Exception("not implemented");
    }
    
    @Operation(summary = "Apagar um trabalho por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Trabalho apagado", 
            content = @Content
        ),
        @ApiResponse(responseCode = "403", description = "Trabalho de outra pessoa, acesso proibído", 
            content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Trabalho não encontrado", 
            content = @Content
        )
    })
    @DeleteMapping("/{id}")
    public void deleteById(@Parameter(description = "ID do trabalho a ser apagado") 
    @PathVariable long id) throws Exception{
        throw new Exception("not implemented");
    }

    @Operation(summary = "Atualizar um trabalho")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Trabalho atualizado", 
            content = @Content
        ),
        @ApiResponse(responseCode = "403", description = "Sem privilégios para atualizar trabalho", 
        content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Trabalho não encontrado", 
        content = @Content
        )
    })        
    @PutMapping("/academicWork/{academicWork}")
    public void updateAcademicWork(@Parameter(description = "Objeto do trabalho sendo atualizado") 
    @PathVariable AcademicWork academicWork) throws Exception{
        throw new Exception("not implemented");
    }  

    @Operation(summary = "Criar um trabalho acadêmico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Trabalho criado", 
            content = @Content
        ),
        @ApiResponse(responseCode = "403", description = "Sem privilégios para criar trabalho", 
        content = @Content
        )
    })  
    @PostMapping("/{academicWork}")
    public void createAcademicWork(@Parameter(description = "ID do trabalho a ser apagado") 
    @PathVariable AcademicWork academicWork) throws Exception{
        throw new Exception("not implemented");
    }    
}
