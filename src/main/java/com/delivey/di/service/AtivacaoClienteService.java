package com.delivey.di.service;

import com.delivey.di.event.ClienteAtivadoEvent;
import com.delivey.di.model.Cliente;
import com.delivey.di.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class AtivacaoClienteService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void ativar(Cliente cliente, Produto produto) {
        cliente.ativar();
        eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente, produto));
    }

}
