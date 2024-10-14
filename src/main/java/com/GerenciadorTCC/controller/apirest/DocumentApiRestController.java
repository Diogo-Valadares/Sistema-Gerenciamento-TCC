package com.GerenciadorTCC.controller.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GerenciadorTCC.controller.assembler.DocumentModelAssembler;
import com.GerenciadorTCC.controller.representationModel.DocumentModel;
import com.GerenciadorTCC.entities.Avaliation;
import com.GerenciadorTCC.entities.Document;
import com.GerenciadorTCC.entities.TaskDeliver;
import com.GerenciadorTCC.service.AvaliationService;
import com.GerenciadorTCC.service.DocumentService;
import com.GerenciadorTCC.service.TaskDeliverService;
import com.GerenciadorTCC.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "api/document")
public class DocumentApiRestController {

    @Autowired
    private DocumentModelAssembler documentModelAssembler;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private AvaliationService avaliationService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskDeliverService taskDeliverService;

    @Operation(summary = "Achar um documento pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Documento encontrado", 
            content = {@Content(mediaType = "application/json", 
                schema = @Schema(implementation = DocumentModel.class))}
        ),
        @ApiResponse(responseCode = "403", description = "Documento de outra pessoa, acesso proibído", 
            content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Documento não encontrado", 
            content = @Content
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<DocumentModel> findById(@Parameter(description = "ID do documento a ser encontrado") 
    @PathVariable long id) {
        var document = documentService.findById(id);
        return document.map(document_ -> ResponseEntity.ok(documentModelAssembler.toModel(document_)))
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Achar um documento pelo título")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Documento encontrado", 
            content = {@Content(mediaType = "application/json", 
                schema = @Schema(implementation = DocumentModel.class))}
        ),
        @ApiResponse(responseCode = "403", description = "Documento de outra pessoa, acesso proibído", 
            content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Documento não encontrado", 
            content = @Content
        )
    })
    @GetMapping("/title/{title}")
    public ResponseEntity<DocumentModel> findByTitle(@Parameter(description = "Título do documento a ser encontrado") 
    @PathVariable String title) {
        var document = documentService.findByTitle(title);
        return document.map(document_ -> ResponseEntity.ok(documentModelAssembler.toModel(document_)))
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Apagar um documento por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Documento apagado", 
            content = @Content
        ),
        @ApiResponse(responseCode = "403", description = "Documento de outra pessoa, acesso proibído", 
            content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Documento não encontrado", 
            content = @Content
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@Parameter(description = "ID do documento a ser apagado")
    @PathVariable long id) {
        documentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar um documento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Documento atualizado", 
            content = {@Content(mediaType = "application/json", 
                schema = @Schema(implementation = DocumentModel.class))}
        ),
        @ApiResponse(responseCode = "403", description = "Documento de outra pessoa, acesso proibído", 
            content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Documento não encontrado", 
            content = @Content
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<DocumentModel> updateDocument(
        @Parameter(description = "ID do documento que esta sendo atualizado")
        @PathVariable long id,
        @RequestBody Document document) {
        if(documentService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var updatedDocument = documentService.update(document);
        return ResponseEntity.ok(documentModelAssembler.toModel(updatedDocument));
    }

    @Operation(summary = "Criar um documento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Documento criado", 
            content = {@Content(mediaType = "application/json", 
                schema = @Schema(implementation = DocumentModel.class))}
        ),
        @ApiResponse(responseCode = "403", description = "Sem privilégios para criar documento", 
            content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada", 
            content = @Content
        )
    })
    @PostMapping("/{taskID}")
    public ResponseEntity<DocumentModel> createDocument(
    @Parameter(description = "ID da tarefa associada ao documento")
    @PathVariable long taskID, @RequestBody Document document) {
        var task = taskService.findById(taskID);
        if (task.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Document createdDocument = documentService.save(document);
        TaskDeliver taskDeliver = new TaskDeliver();
        taskDeliver.setDocument(createdDocument);
        taskDeliver.setTask(task.get());        
        taskDeliverService.save(taskDeliver);        
        DocumentModel documentModel = documentModelAssembler.toModel(createdDocument);
        return ResponseEntity.status(HttpStatus.CREATED).body(documentModel);
    }

    @Operation(summary = "Adicionar Avaliação a um documento")    
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avaliação adicionada", 
            content = {@Content(mediaType = "application/json", 
                schema = @Schema(implementation = Avaliation.class))}
        ),
        @ApiResponse(responseCode = "403", description = "Sem privilégios para adicionar avaliação", 
            content = @Content
        )
    })
    @PostMapping("/{id}/avaliation")
    public ResponseEntity<Void> addAvaliation(
        @Parameter(description = "ID do documento que terá uma avaliação adicionada")
        @PathVariable long id, @RequestBody Avaliation avaliation) {
        avaliationService.addAvaliationToDocument(id, avaliation);
        return ResponseEntity.ok().build();
    }
}
