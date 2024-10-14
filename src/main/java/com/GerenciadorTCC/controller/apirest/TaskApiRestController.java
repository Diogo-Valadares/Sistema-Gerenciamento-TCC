package com.GerenciadorTCC.controller.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GerenciadorTCC.controller.assembler.TaskModelAssembler;
import com.GerenciadorTCC.controller.representationModel.TaskModel;
import com.GerenciadorTCC.entities.Task;
import com.GerenciadorTCC.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "api/task")
public class TaskApiRestController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskModelAssembler taskModelAssembler;

    @Operation(summary = "Achar uma tarefa pelo título")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa encontrada", 
            content = {@Content(mediaType = "application/json", 
                schema = @Schema(implementation = TaskModel.class))}
        ),
        @ApiResponse(responseCode = "403", description = "Tarefa de outra pessoa, acesso proibído", 
            content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada", 
            content = @Content
        )
    })
    @GetMapping("/title/{title}")
    public ResponseEntity<TaskModel> findByTitle(
    @Parameter(description = "Título da tarefa a ser encontrada") 
    @PathVariable String title) {
        var task = taskService.findByTitle(title);
        return task.map(task_ -> ResponseEntity.ok(taskModelAssembler.toModel(task_)))
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Achar uma tarefa pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa encontrada", 
            content = {@Content(mediaType = "application/json", 
                schema = @Schema(implementation = TaskModel.class))}
        ),
        @ApiResponse(responseCode = "403", description = "Tarefa de outra pessoa, acesso proibído", 
            content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada", 
            content = @Content
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskModel> findById(
    @Parameter(description = "ID da tarefa a ser encontrada") 
    @PathVariable long id) {
        var task = taskService.findById(id);
        return task.map(task_ -> ResponseEntity.ok(taskModelAssembler.toModel(task_)))
                     .orElseGet(() -> ResponseEntity.notFound().build());
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
    public ResponseEntity<Void> deleteById(@Parameter(description = "ID da tarefa a ser apagada")
    @PathVariable long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar uma tarefa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa atualizada", 
            content = {@Content(mediaType = "application/json", 
                schema = @Schema(implementation = TaskModel.class))}
        ),
        @ApiResponse(responseCode = "403", description = "Tarefa de outra pessoa, acesso proibído", 
            content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada", 
            content = @Content
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskModel> updateTask(@Parameter(description = "ID da tarefa a ser atualizada")
    @PathVariable long id, @RequestBody Task task) {
        var foundTask = taskService.findById(id);
        if (!foundTask.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        var updatedTask = taskService.update(task);
        return ResponseEntity.ok(taskModelAssembler.toModel(updatedTask));
    }

    @Operation(summary = "Criar uma tarefa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tarefa criada", 
            content = {@Content(mediaType = "application/json", 
                schema = @Schema(implementation = TaskModel.class))}
        ),
        @ApiResponse(responseCode = "403", description = "Sem privilégios para criar tarefa", 
            content = @Content
        )
    })
    @PostMapping("/{id}")
    public ResponseEntity<TaskModel> createTask(@Parameter(description = "ID da tarefa sendo criada")
    @PathVariable long id, @RequestBody Task task) {
        var foundTask = taskService.findById(id);
        if (!foundTask.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        var savedTask = taskService.save(task);
        return ResponseEntity.status(201).body(taskModelAssembler.toModel(savedTask));
    }
}
