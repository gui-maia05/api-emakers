package com.br.emakers.api_emakers.data.entity;

import com.br.emakers.api_emakers.data.dto.request.LivroRequestDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "autor", nullable = false, length = 45)
    private String autor;

    @Column(name = "data_lancamento", nullable = false)
    private Date dataLancamento;

    @Column(name = "livro_disponivel", nullable = false)
    private boolean isDisponivel = true;

    @Builder
    public Livro(LivroRequestDTO livroRequestDTO) {
        this.nome = livroRequestDTO.nome();
        this.autor = livroRequestDTO.autor();
        this.dataLancamento = livroRequestDTO.data_lancamento();
        this.isDisponivel = true;
    }

    public boolean isDisponivel() {
        return isDisponivel;
    }
    public void setDisponivel(boolean disponivel){
        this.isDisponivel = disponivel;

    }

}