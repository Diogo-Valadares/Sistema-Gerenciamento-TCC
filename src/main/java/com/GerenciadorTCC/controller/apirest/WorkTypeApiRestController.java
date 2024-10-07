package com.GerenciadorTCC.controller.apirest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GerenciadorTCC.entities.WorkType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "api/workType")
public class WorkTypeApiRestController {

    @Operation(summary = "Achar um tipo de trabalho pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de trabalho encontrado", 
            content = {@Content(mediaType = "application/json", 
                schema = @Schema(implementation = WorkType.class))}
        ),
        @ApiResponse(responseCode = "404", description = "Tipo de trabalho não encontrado", 
        content = @Content
        )
    })
    @GetMapping("/{id}")
    public WorkType findById(
    @Parameter(description = "Titulo do tipo de trabalho a ser encontrado")
    @PathVariable long id) throws Exception{
        throw new Exception("not implemented");
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
    @PutMapping("/{workType}")
    public void updateWorkType(@Parameter(description = "Objeto do tipo de trabalho sendo atualizado")
    @PathVariable WorkType workType) throws Exception{
        throw new Exception("not implemented");
    }
}
