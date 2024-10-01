package com.GerenciadorTCC.controller.apirest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GerenciadorTCC.entities.Document;
import com.GerenciadorTCC.entities.Person;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = "api/document")
public class DocumentApiRestController {

    @Operation(summary = "Achar um documento pelo titulo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Documento encontrado", 
            content = {@Content(mediaType = "application/json", 
                schema = @Schema(implementation = Document.class))}
        ),
        @ApiResponse(responseCode = "403", description = "Documento de outra pessoa, acesso proibído", 
        content = @Content
        ),
        @ApiResponse(responseCode = "404", description = "Documento não encontrado", 
        content = @Content
        )
    })
    @GetMapping("/{title}")
    public Document findByTitle(@Parameter(description = "Titulo do documento a ser encontrado") 
    @PathVariable String title) throws Exception{
        throw new Exception("not implemented");
    }
}
