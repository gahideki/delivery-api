package com.delivey.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public EstadoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public EstadoNaoEncontradoException(Long id) {
        this(String.format("Estado de código %d não foi encontrado", id));
    }

}
