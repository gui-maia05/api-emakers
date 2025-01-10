package com.br.emakers.api_emakers.data.dto.response;

import com.br.emakers.api_emakers.data.entity.Pessoa;

public record PessoaResponseDTO(
        Long idPessoa,

        String nome,

        String cep
) {
    public PessoaResponseDTO(Pessoa pessoa){
        this(pessoa.getIdPessoa(), pessoa.getNome(), pessoa.getCep());
    }
}
