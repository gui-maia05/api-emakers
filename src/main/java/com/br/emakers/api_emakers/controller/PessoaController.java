package com.br.emakers.api_emakers.controller;

import com.br.emakers.api_emakers.data.dto.request.PessoaRequestDTO;
import com.br.emakers.api_emakers.data.dto.response.LivroResponseDTO;
import com.br.emakers.api_emakers.data.dto.response.PessoaResponseDTO;
import com.br.emakers.api_emakers.service.PessoaService;
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
@RequestMapping("/pessoa")
@Tag(name = "Pessoas", description = "Endpoints relacionados à área de pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;


    @Operation(summary = "Lista todas as pessoas do sistema.",
            description = "Lista todas as pessoas que estão no sistema.",
            tags = {"Pessoas"},
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
    public ResponseEntity<List<PessoaResponseDTO>> getAllPessoas() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getAllPessoas());
    }


    @Operation(summary = "Busca uma pessoa.",
            description = "Busca uma pessoa de acordo com o ID informado.",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LivroResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "/{idPessoa}")
    public ResponseEntity<PessoaResponseDTO> getPessoaById(@PathVariable Long idPessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getPessoaById(idPessoa));
    }


    @Operation(summary = "Cria uma nova pessoa.",
            description = "Esse método cria uma nova pessoa com base nos dados fornecidos pelo cliente em um objeto do tipo LivroRequestDTO.",
            tags = {"Pessoas"},
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
    public ResponseEntity<PessoaResponseDTO> createPessoa(@RequestBody PessoaRequestDTO pessoaRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.createPessoa(pessoaRequestDTO));
    }


    @Operation(summary = "Atualiza os dados de uma pessoa.",
            description = "Esse método atualiza os dados de uma pessoa existente com base no ID fornecido e nas novas informações enviadas pelo cliente.",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LivroResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PutMapping(value = "/update/{idPessoa}")
    public ResponseEntity<PessoaResponseDTO> updatePessoa(@PathVariable Long idPessoa ,@RequestBody PessoaRequestDTO pessoaRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.updatePessoa(idPessoa,pessoaRequestDTO));
    }


    @Operation(summary = "Exclui uma pessoa.",
            description = "Esse método exclui uma pessoa existente com base no ID fornecido pelo cliente.",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LivroResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @DeleteMapping(value = "/delete/{idPessoa}")
    public ResponseEntity<String> deletePessoa(@PathVariable Long idPessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.deletePessoa(idPessoa));
    }
}