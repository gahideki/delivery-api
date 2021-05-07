package com.delivey.di.service;

import com.delivey.di.model.Cliente;
import com.delivey.di.model.Produto;
import com.delivey.di.notificacao.Notificador;

public class EmissaoNotaFiscalService {

    private final Notificador notificador;

    public EmissaoNotaFiscalService(Notificador notificador) {
        this.notificador = notificador;
    }

    public void emitir(Cliente cliente, Produto produto) {
        // TODO emite NF aqui
        notificador.notificar(cliente, String.format("NF do produto %s foi emitida!", produto.getNome()));
    }

}
