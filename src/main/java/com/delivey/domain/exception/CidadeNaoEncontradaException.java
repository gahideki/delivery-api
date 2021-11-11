package com.delivey.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

    public CidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public CidadeNaoEncontradaException(Long id) {
        this(String.format("Cidade de código %d não foi encontrada", id));
    }

}
