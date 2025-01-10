package com.br.emakers.api_emakers.service;


import com.br.emakers.api_emakers.data.dto.request.LivroRequestDTO;
import com.br.emakers.api_emakers.data.dto.response.LivroResponseDTO;
import com.br.emakers.api_emakers.data.entity.Livro;
import com.br.emakers.api_emakers.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<LivroResponseDTO> getAllLivros() {
        List<Livro> livros = livroRepository.findAll();

        return livros.stream().map(LivroResponseDTO::new).collect(Collectors.toList());
    }

    private Livro getLivroEntityById(Long idLivro) {
        return livroRepository.findById(idLivro).orElseThrow(()-> new RuntimeException("Esse livro não foi encontrado"));
    }

    public LivroResponseDTO getLivroById(Long idLivro) {
        Livro livro = getLivroEntityById(idLivro);

        return new LivroResponseDTO(livro);
    }

    public LivroResponseDTO createLivro(LivroRequestDTO livroRequestDTO) {
        Livro livro = new Livro(livroRequestDTO);
        livroRepository.save(livro);

        return new LivroResponseDTO(livro);
    }

    public LivroResponseDTO updateLivro(Long idLivro, LivroRequestDTO livroRequestDTO) {
        Livro livro = getLivroEntityById(idLivro);

        livro.setNome(livroRequestDTO.nome());
        livroRepository.save(livro);

        return new LivroResponseDTO(livro);
    }

    public String deleteLivro(Long idLivro) {
        Livro livro = getLivroEntityById(idLivro);
        livroRepository.delete(livro);
        return "O livro " + idLivro + " foi removido";
    }

}
