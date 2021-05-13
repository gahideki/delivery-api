package com.delivey.di.notificacao;

import com.delivey.di.model.Cliente;

public interface Notificador {

    void notificar(Cliente cliente, String mensagem);

}
