package com.br.emakers.api_emakers.controller;

import com.br.emakers.api_emakers.data.dto.request.EmprestimoRequestDTO;
import com.br.emakers.api_emakers.data.dto.response.EmprestimoResponseDTO;
import com.br.emakers.api_emakers.service.EmprestimoService;
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
@RequestMapping("emprestimos")
@Tag(name = "Empréstimos", description = "Endpoints relacionados à área de empréstimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;


    @Operation(summary = "Lista todos os empréstimos do sistema.",
            description = "Lista todos os empréstimos que ainda não foram encerrados.",
            tags = {"Empréstimos"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EmprestimoResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "/all")
    public ResponseEntity<List<EmprestimoResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getAllEmprestimos());
    }


    @Operation(summary = "Realiza um empréstimo.",
            description = "Realiza um empréstimo de acordo com o ID que foi dado de uma pessoa e de um livro.",
            tags = {"Empréstimos"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EmprestimoResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping("/fazerEmprestimo")
    public ResponseEntity<EmprestimoResponseDTO> realizarEmprestimo(@RequestBody EmprestimoRequestDTO emprestimoRequestDTO) {
        EmprestimoResponseDTO response = emprestimoService.realizarEmprestimo(emprestimoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @Operation(summary = "Devolução de um empréstimo.",
            description = "Realiza a devolução de um livro de acordo com o ID do empréstimo.",
            tags = {"Empréstimos"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EmprestimoResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PutMapping("/devolver/{idEmprestimo}")
    public ResponseEntity<EmprestimoResponseDTO> devolverEmprestimo(@PathVariable Long idEmprestimo) {
        EmprestimoResponseDTO response = emprestimoService.devolverEmprestimo(idEmprestimo);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}



