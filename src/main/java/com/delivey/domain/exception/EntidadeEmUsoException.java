package com.delivey.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntidadeEmUsoException extends ResponseStatusException {

    public EntidadeEmUsoException(HttpStatus status, String mensagem) {
        super(status, mensagem);
    }

    public EntidadeEmUsoException(String mensagem) {
        this(HttpStatus.CONFLICT, mensagem);
    }

}
