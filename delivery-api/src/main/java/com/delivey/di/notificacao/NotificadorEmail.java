package com.delivey.di.notificacao;

import com.delivey.di.model.Cliente;

public class NotificadorEmail implements Notificador {

    private boolean caixaAlta;
    private String hostServidorSmtp;

    public NotificadorEmail(String hostServidorSmtp) {
        this.hostServidorSmtp = hostServidorSmtp;
    }

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        if (caixaAlta) {
            mensagem = mensagem.toUpperCase();
        }
        System.out.printf("Notificando %s através do e-mail %s: %s%n", cliente.getNome(), cliente.getEmail(), mensagem);
    }

}
