package com.GerenciadorTCC.controller.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GerenciadorTCC.controller.assembler.WorkTypeModelAssembler;
import com.GerenciadorTCC.controller.representationModel.WorkTypeModel;
import com.GerenciadorTCC.entities.WorkType;
import com.GerenciadorTCC.service.WorkTypeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "api/workType")
public class WorkTypeApiRestController {

    @Autowired
    private WorkTypeService workTypeService;
    @Autowired
    private WorkTypeModelAssembler workTypeAssembler;

    @Operation(summary = "Achar um tipo de trabalho pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de trabalho encontrado", 
            content = {@Content(mediaType = "application/json", 
                schema = @Schema(implementation = WorkTypeModel.class))}
        ),
        @ApiResponse(responseCode = "404", description = "Tipo de trabalho não encontrado", 
        content = @Content
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<WorkTypeModel> findById(
    @Parameter(description = "Titulo do tipo de trabalho a ser encontrado")
    @PathVariable long id) {
        return workTypeService.findById(id)
                .map(workType -> ResponseEntity.ok(workTypeAssembler.toModel(workType)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Achar um tipo de trabalho pelo nome")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de trabalho encontrado", 
            content = {@Content(mediaType = "application/json", 
                schema = @Schema(implementation = WorkTypeModel.class))}
        ),
        @ApiResponse(responseCode = "404", description = "Tipo de trabalho não encontrado", 
        content = @Content
        )
    })
    @GetMapping("/name/{name}")
    public ResponseEntity<WorkTypeModel> findByName(
    @Parameter(description = "Nome do tipo de trabalho a ser encontrado")
    @PathVariable String name) {
        return workTypeService.findByName(name)
                .map(workType -> ResponseEntity.ok(workTypeAssembler.toModel(workType)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar um tipo de trabalho")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Tipo de trabalho atualizado", 
            content = @Content
        ),
        @ApiResponse(responseCode = "403", description = "Apenas para uso administrativo, acesso proibído", 
            content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Tipo de trabalho não encontrado", 
            content = @Content
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateWorkType(@Parameter(description = "ID do tipo de trabalho a ser atualizado")
    @PathVariable long id, @Parameter(description = "Objeto do tipo de trabalho sendo atualizado")
    @RequestBody WorkType workType) {
        var optional = workTypeService.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        workTypeService.save(optional.get());
        return ResponseEntity.noContent().build();
    }
}
