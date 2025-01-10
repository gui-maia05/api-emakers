package com.br.emakers.api_emakers.controller;

import com.br.emakers.api_emakers.data.dto.request.EmprestimoRequestDTO;
import com.br.emakers.api_emakers.data.dto.response.EmprestimoResponseDTO;
import com.br.emakers.api_emakers.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    /**
     * Endpoint para realizar um empréstimo.
     *
     * @param emprestimoRequestDTO Dados do empréstimo.
     * @return Lista de todos os empréstimos atualizada.
     */
    @PostMapping
    public ResponseEntity<List<EmprestimoResponseDTO>> fazerEmprestimo(@RequestBody EmprestimoRequestDTO emprestimoRequestDTO) {
        List<EmprestimoResponseDTO> emprestimos = emprestimoService.fazerEmprestimos(emprestimoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimos);
    }

    /**
     * Endpoint para devolver um livro.
     *
     * @param idEmprestimo ID do empréstimo.
     * @return Dados do empréstimo após a devolução.
     */
    @PutMapping("/{idEmprestimo}/devolucao")
    public ResponseEntity<EmprestimoResponseDTO> fazerDevolucaoEmprestimo(@PathVariable Long idEmprestimo) {
        EmprestimoResponseDTO emprestimo = emprestimoService.devolverLivro(idEmprestimo);
        return ResponseEntity.status(HttpStatus.OK).body(emprestimo);
    }
}
