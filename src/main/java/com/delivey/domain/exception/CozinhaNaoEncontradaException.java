package com.delivey.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

    public CozinhaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public CozinhaNaoEncontradaException(Long id) {
        this(String.format("Cozinha de código %d não foi encontrada", id));
    }

}
