package com.br.emakers.api_emakers.data.dto.request;

import com.br.emakers.api_emakers.data.entity.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public record LivroRequestDTO(

        @NotBlank(message = "O nome do livro é obrigatório")
        String nome,

        @NotBlank(message = "O nome do autor é obrigatório")
        String autor,

        @NotNull(message = "A data de lançamento é obrigatória")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        @PastOrPresent(message = "A data de lançamento do livro não pode ser no futuro")
        Date data_lancamento
) {
}
