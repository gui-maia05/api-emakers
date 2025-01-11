package com.br.emakers.api_emakers.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public record RestErrorMessage(
        HttpStatus status,
        String message,
        Date timestamp
) {
    public RestErrorMessage(HttpStatus status, String message) {
        this(status, message, new Date());
    }
}
