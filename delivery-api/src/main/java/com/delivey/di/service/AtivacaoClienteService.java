package com.delivey.di.service;

import com.delivey.di.anotacao.TipoDoNotificador;
import com.delivey.di.model.Cliente;
import com.delivey.di.notificacao.Notificador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.delivey.di.anotacao.NivelUrgencia.NORMAL;

// @Service
public class AtivacaoClienteService {

    private Logger logger = LoggerFactory.getLogger(AtivacaoClienteService.class);

    @TipoDoNotificador(NORMAL)
    @Autowired
    private Notificador notificador;

    // @PostConstruct
    public void init() {
        logger.info("INIT: {}", notificador);
    }

    // @PreDestroy
    public void destroy() {
        logger.info("DESTROY: {}", notificador);
    }

    public void ativar(Cliente cliente) {
        cliente.ativar();
        notificador.notificar(cliente, "Cliente ativo com sucesso!");
    }

}
