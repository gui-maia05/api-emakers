package com.br.emakers.api_emakers.data.dto.response;

import com.br.emakers.api_emakers.data.entity.Livro;

import java.util.Date;

public record LivroResponseDTO(

        Long IdLivro,

        String nome,

        String autor,

        Date dataLancamento
) {

    public LivroResponseDTO(Livro livro){
        this(livro.getIdLivro(), livro.getNome(), livro.getAutor(), livro.getDataLancamento());
    }
}
