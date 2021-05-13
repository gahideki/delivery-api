package com.delivey.di.listener;

import com.delivey.di.anotacao.TipoDoNotificador;
import com.delivey.di.event.ClienteAtivadoEvent;
import com.delivey.di.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.delivey.di.anotacao.NivelUrgencia.NORMAL;

@Component
public class EmissaoNotaFiscalListener {

    @TipoDoNotificador(NORMAL)
    @Autowired
    private Notificador notificador;

    @Order(2)
    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent event) {
        notificador.notificar(event.getCliente(), String.format("NF do produto %s foi emitida!", event.getProduto().getNome()));
    }

}
