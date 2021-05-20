package com.delivey.di.listener;

import com.delivey.di.anotacao.TipoDoNotificador;
import com.delivey.di.event.ClienteAtivadoEvent;
import com.delivey.di.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.delivey.di.anotacao.NivelUrgencia.URGENTE;

@Component
public class NotificacaoListener {

    @TipoDoNotificador(URGENTE)
    @Autowired
    private Notificador notificador;

    @Order(1)
    @EventListener(condition = "#event.temTelefone()") // Spring Expression Language
    public void clienteAtivadoListener(ClienteAtivadoEvent event) {
        notificador.notificar(event.getCliente(), "está ativo!");
    }

}
