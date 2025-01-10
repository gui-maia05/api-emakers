package com.br.emakers.api_emakers.repository;

import com.br.emakers.api_emakers.data.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
}
