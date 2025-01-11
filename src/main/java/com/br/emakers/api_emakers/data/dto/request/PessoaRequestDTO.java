package com.br.emakers.api_emakers.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PessoaRequestDTO(

        @NotBlank(message = "O nome da pessoa é obrigatório.")
        String nome,

        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve seguir o formato 00000-000.")
        @NotBlank(message = "O cep é obrigatório.")
        String cep

) {
}
