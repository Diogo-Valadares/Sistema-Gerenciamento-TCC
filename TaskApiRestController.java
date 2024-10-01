package com.GerenciadorTCC.controller.apirest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GerenciadorTCC.entities.Person;
import com.GerenciadorTCC.entities.Task;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = "api/task")
public class TaskApiRestController {

    @Operation(summary = "Achar uma tarefa pelo titulo")
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
    @GetMapping("/{title}")
    public Task findByTitle(@Parameter(description = "Titulo da tarefa a ser encontrada") 
    @PathVariable String title) throws Exception{
        throw new Exception("not implemented");
    }
}
