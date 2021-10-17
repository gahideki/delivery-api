package com.delivey.di.notificacao;

import com.delivey.di.anotacao.TipoDoNotificador;
import com.delivey.di.model.Cliente;
import org.springframework.stereotype.Component;

import static com.delivey.di.anotacao.NivelUrgencia.URGENTE;

@TipoDoNotificador(URGENTE)
@Component
public class NotificadorSMS implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s por SMS através do telefone %s: %s%n", cliente.getNome(), cliente.getTelefone(), mensagem);
    }

}
