package com.GerenciadorTCC.controller.apirest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GerenciadorTCC.entities.Avaliation;
import com.GerenciadorTCC.entities.Document;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "api/document")
public class DocumentApiRestController {

    @Operation(summary = "Achar um documento pelo ID")
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
    @GetMapping("/{id}")
    public Document findByID(@Parameter(description = "Titulo do documento a ser encontrado") 
    @PathVariable long title) throws Exception{
        throw new Exception("not implemented");
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
    public void deleteById(@Parameter(description = "ID do documento a ser apagado")
    @PathVariable long id) throws Exception{
        throw new Exception("not implemented");
    }

    @Operation(summary = "Atualizar um documento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Documento atualizado", 
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
    @PutMapping("/{document}")
    public Document updateDocument(@Parameter(description = "Documento a ser atualizado")
    @PathVariable Document document) throws Exception{
        throw new Exception("not implemented");
    }

    @Operation(summary = "Criar um documento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Documento criado", 
            content = {@Content(mediaType = "application/json", 
                schema = @Schema(implementation = Document.class))}
        ),
        @ApiResponse(responseCode = "403", description = "Sem privilégios para criar documento", 
        content = @Content
        )
    })
    @PostMapping("/{document}&{taskID}")
    public Document createDocument(
    @Parameter(description = "Documento a ser criado")
    @PathVariable Document document,
    @Parameter(description = "ID da tarefa a ser associada")
    @PathVariable long taskID
    ) throws Exception{
        throw new Exception("not implemented");
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
    @PostMapping("/{id}/{avaliation}")
    public void addAvaliation(
    @Parameter(description = "ID do documento que tera uma avaliação adicionada")
    @PathVariable long id,
    @Parameter(description = "avaliação a ser adicionada")
    @PathVariable Avaliation avaliation
    ) throws Exception{
        throw new Exception("not implemented");
    }
}
