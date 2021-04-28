package com.delivey.di.service;

import com.delivey.di.model.Cliente;
import com.delivey.di.notificacao.Notificador;

public class AtivacaoClienteService {

    private final Notificador notificador;

    public AtivacaoClienteService(Notificador notificador) {
        this.notificador = notificador;
    }

    public void ativar(Cliente cliente) {
        cliente.ativar();
        notificador.notificar(cliente, "Cliente ativo com sucesso!");
    }

}
