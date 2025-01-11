package com.br.emakers.api_emakers.controller;

import com.br.emakers.api_emakers.data.dto.request.LivroRequestDTO;
import com.br.emakers.api_emakers.data.dto.request.PessoaRequestDTO;
import com.br.emakers.api_emakers.data.dto.response.EmprestimoResponseDTO;
import com.br.emakers.api_emakers.data.dto.response.LivroResponseDTO;
import com.br.emakers.api_emakers.data.dto.response.PessoaResponseDTO;
import com.br.emakers.api_emakers.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
@Tag(name = "Livros", description = "Endpoints relacionados à área de livros")
public class LivroController {

    @Autowired
    private LivroService serviceLivro;


    @Operation(summary = "Lista todos os livros do sistema.",
            description = "Lista todos os livros que estão no sistema.",
            tags = {"Livros"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LivroResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "/all")
    public ResponseEntity<List<LivroResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(serviceLivro.getAllLivros());
    }


    @Operation(summary = "Busca um livro.",
            description = "Busca um livro de acordo com o ID informado.",
            tags = {"Livros"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LivroResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "/{idLivro}")
    public ResponseEntity<LivroResponseDTO> findById(@PathVariable Long idLivro) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceLivro.getLivroById(idLivro));
    }


    @Operation(summary = "Cria um novo livro.",
            description = "Esse método cria um novo livro com base nos dados fornecidos pelo cliente em um objeto do tipo LivroRequestDTO.",
            tags = {"Livros"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LivroResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping(value = "/create")
    public ResponseEntity<LivroResponseDTO> createLivro(@RequestBody LivroRequestDTO livroRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceLivro.createLivro(livroRequestDTO));
    }


    @Operation(summary = "Atualiza os dados de um livro.",
            description = "Esse método atualiza os dados de um livro existente com base no ID fornecido e nas novas informações enviadas pelo cliente.",
            tags = {"Livros"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LivroResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PutMapping(value = "/update/{idLivro}")
    public ResponseEntity<LivroResponseDTO> updateLivro(@PathVariable Long idLivro ,@RequestBody LivroRequestDTO livroRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceLivro.updateLivro(idLivro, livroRequestDTO));
    }


    @Operation(summary = "Exclui um livro.",
            description = "Esse método exclui um livro existente com base no ID fornecido pelo cliente.",
            tags = {"Livros"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LivroResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @DeleteMapping(value = "/delete/{idLivro}")
    public ResponseEntity<String> deleteLivro(@PathVariable Long idLivro) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceLivro.deleteLivro(idLivro));
    }

}
