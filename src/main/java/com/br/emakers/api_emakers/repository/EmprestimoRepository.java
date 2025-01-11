package com.br.emakers.api_emakers.repository;

import com.br.emakers.api_emakers.data.entity.Emprestimo;
import com.br.emakers.api_emakers.data.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findByDataDevolucaoIsNull();
    long countByPessoaAndDataDevolucaoIsNull(Pessoa pessoa);

}


