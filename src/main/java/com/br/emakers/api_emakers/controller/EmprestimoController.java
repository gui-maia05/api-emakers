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
@RequestMapping("emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<EmprestimoResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getAllEmprestimos());
    }


    @PostMapping("/fazerEmprestimo")
    public ResponseEntity<EmprestimoResponseDTO> realizarEmprestimo(@RequestBody EmprestimoRequestDTO emprestimoRequestDTO) {
        EmprestimoResponseDTO response = emprestimoService.realizarEmprestimo(emprestimoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/devolver/{idEmprestimo}")
    public ResponseEntity<EmprestimoResponseDTO> devolverEmprestimo(@PathVariable Long idEmprestimo) {
        EmprestimoResponseDTO response = emprestimoService.devolverEmprestimo(idEmprestimo);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}



