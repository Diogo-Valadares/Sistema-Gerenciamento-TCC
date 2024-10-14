package com.GerenciadorTCC.controller.apirest;

import java.util.Optional;

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

import com.GerenciadorTCC.controller.assembler.AcademicWorkModelAssembler;
import com.GerenciadorTCC.controller.representationModel.AcademicWorkModel;
import com.GerenciadorTCC.entities.AcademicWork;
import com.GerenciadorTCC.service.AcademicWorkService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "api/academicWork")
public class AcademicWorkApiRestController {

    @Autowired
    private AcademicWorkModelAssembler academicWorkModelAssembler;
    @Autowired
    private AcademicWorkService academicWorkService;   

    @Operation(summary = "Achar um trabalho por ID")
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
    @GetMapping("/{id}")
    public ResponseEntity<AcademicWorkModel> findByID(@Parameter(description = "ID do trabalho a ser encontrado") 
    @PathVariable long id) {
        Optional<AcademicWork> academicWork = academicWorkService.findById(id);
        return academicWork.map(work -> ResponseEntity.ok(academicWorkModelAssembler.toModel(work)))
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

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
    @GetMapping("/title/{title}")
    public ResponseEntity<AcademicWorkModel> findByTitle(@Parameter(description = "Titulo do trabalho a ser encontrado") 
    @PathVariable String title) {
        Optional<AcademicWork> academicWork = academicWorkService.findByTitle(title);
        return academicWork.map(work -> ResponseEntity.ok(academicWorkModelAssembler.toModel(work)))
                           .orElseGet(() -> ResponseEntity.notFound().build());
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
    public ResponseEntity<Void> deleteById(@Parameter(description = "ID do trabalho a ser apagado") 
    @PathVariable long id) {
        Optional<AcademicWork> academicWork = academicWorkService.findById(id);
        if (academicWork.isPresent()) {
            academicWorkService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Atualizar um trabalho")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Trabalho atualizado", 
            content = @Content
        ),
        @ApiResponse(responseCode = "403", description = "Sem privilégios para atualizar trabalho", 
            content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Trabalho não encontrado", 
            content = @Content
        )
    })        
    @PutMapping("/{academicWork}")
    public ResponseEntity<AcademicWorkModel> updateAcademicWork(@Parameter(description = "ID do trabalho a ser atualizado") 
    @RequestBody AcademicWork academicWork) {
        var updatedAcademicWork = academicWorkService.update(academicWork);
        return ResponseEntity.ok(academicWorkModelAssembler.toModel(updatedAcademicWork));
    }  
    
    @Operation(summary = "Criar um trabalho acadêmico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Trabalho criado", 
            content = @Content
        ),
        @ApiResponse(responseCode = "403", description = "Sem privilégios para criar trabalho", 
            content = @Content
        )
    })  
    @PostMapping
    public ResponseEntity<Void> createAcademicWork(@RequestBody AcademicWork academicWork) {
        academicWorkService.save(academicWork);
        return ResponseEntity.status(201).build();
    }    
}
