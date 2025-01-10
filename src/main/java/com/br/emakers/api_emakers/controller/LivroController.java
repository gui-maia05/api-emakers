package com.br.emakers.api_emakers.controller;

import com.br.emakers.api_emakers.data.dto.request.LivroRequestDTO;
import com.br.emakers.api_emakers.data.dto.request.PessoaRequestDTO;
import com.br.emakers.api_emakers.data.dto.response.LivroResponseDTO;
import com.br.emakers.api_emakers.data.dto.response.PessoaResponseDTO;
import com.br.emakers.api_emakers.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService serviceLivro;
    @Autowired
    private LivroService livroService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<LivroResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(serviceLivro.getAllLivros());
    }

    @GetMapping(value = "/{idLivro}")
    public ResponseEntity<LivroResponseDTO> findById(@PathVariable Long idLivro) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceLivro.getLivroById(idLivro));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<LivroResponseDTO> createLivro(@RequestBody LivroRequestDTO livroRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceLivro.createLivro(livroRequestDTO));
    }

    @PutMapping(value = "/update/{idLivro}")
    public ResponseEntity<LivroResponseDTO> updateLivro(@PathVariable Long idLivro ,@RequestBody LivroRequestDTO livroRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.updateLivro(idLivro, livroRequestDTO));
    }

    @DeleteMapping(value = "/delete/{idLivro}")
    public ResponseEntity<String> deleteLivro(@PathVariable Long idLivro) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.deleteLivro(idLivro));
    }

}
