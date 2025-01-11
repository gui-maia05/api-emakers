package com.br.emakers.api_emakers.data.dto.response;

import com.br.emakers.api_emakers.data.entity.Emprestimo;
import com.br.emakers.api_emakers.data.entity.Livro;
import com.br.emakers.api_emakers.data.entity.Pessoa;

import java.time.LocalDate;
import java.util.Date;

public record EmprestimoResponseDTO(

        Long IdEmprestimo,

        Pessoa pessoa,

        Livro livro,

        Date dataEmprestimo,

        Date dataDevolucao
) {

    public EmprestimoResponseDTO(Emprestimo emprestimo){
        this(emprestimo.getIdEmprestimo(), emprestimo.getPessoa(), emprestimo.getLivro(), emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao());
    }
}
