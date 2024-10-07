package com.GerenciadorTCC.controller.apirest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GerenciadorTCC.entities.Task;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "api/task")
public class TaskApiRestController {

    @Operation(summary = "Achar uma tarefa pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa encontrada", 
            content = {@Content(mediaType = "application/json", 
                schema = @Schema(implementation = Task.class))}
        ),
        @ApiResponse(responseCode = "403", description = "Tarefa de outra pessoa, acesso proibído", 
        content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada", 
        content = @Content
        )
    })
    @GetMapping("/{id}")
    public Task findByTitle(
    @Parameter(description = "Titulo da tarefa a ser encontrada") 
    @PathVariable String id) throws Exception{
        throw new Exception("not implemented");
    }

    @Operation(summary = "Apagar uma tarefa por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Tarefa apagada", 
            content = @Content
        ),
        @ApiResponse(responseCode = "403", description = "Tarefa de outra pessoa, acesso proibído", 
            content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada", 
            content = @Content
        )
    })
    @DeleteMapping("/{id}")
    public void deleteById(@Parameter(description = "ID da tarefa a ser apagada")
    @PathVariable long id) throws Exception{
        throw new Exception("not implemented");
    }

    @Operation(summary = "Atualizar uma tarefa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Tarefa atualizada", 
            content = @Content
        ),
        @ApiResponse(responseCode = "403", description = "Tarefa de outra pessoa, acesso proibído", 
            content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada", 
            content = @Content
        )
    })
    @PutMapping("/{task}")
    public void updateTask(@Parameter(description = "Objeto da tarefa sendo atualizada")
    @PathVariable Task task) throws Exception{
        throw new Exception("not implemented");
    }

    @Operation(summary = "Criar uma tarefa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Tarefa criada", 
            content = @Content
        ),
        @ApiResponse(responseCode = "403", description = "Sem privilégios para criar tarefa", 
            content = @Content
        )
    })
    @PostMapping("/{task}")
    public void createTask(@Parameter(description = "Objeto da tarefa sendo criada")
    @PathVariable Task task) throws Exception{
        throw new Exception("not implemented");
    }
}
