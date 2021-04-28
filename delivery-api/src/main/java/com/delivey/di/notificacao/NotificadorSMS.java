package com.delivey.di.notificacao;

import com.delivey.di.model.Cliente;

public class NotificadorSMS implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s por SMS através do telefone %s: %s%n", cliente.getNome(), cliente.getTelefone(), mensagem);
    }

}
