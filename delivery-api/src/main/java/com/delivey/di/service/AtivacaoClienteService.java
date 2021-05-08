package com.delivey.di.service;

import com.delivey.di.anotacao.TipoDoNotificador;
import com.delivey.di.model.Cliente;
import com.delivey.di.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.delivey.di.anotacao.NivelUrgencia.NORMAL;

@Service
public class AtivacaoClienteService {

    @TipoDoNotificador(NORMAL)
    @Autowired
    private Notificador notificador;

    public void ativar(Cliente cliente) {
        cliente.ativar();
        notificador.notificar(cliente, "Cliente ativo com sucesso!");
    }

}
