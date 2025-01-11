package com.br.emakers.api_emakers.exceptions.general;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id) {
        super("Entidade com ID: " + id + " não encontrada!");
    }
}
