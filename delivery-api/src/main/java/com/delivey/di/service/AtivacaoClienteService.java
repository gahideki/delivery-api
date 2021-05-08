package com.delivey.di.service;

import com.delivey.di.model.Cliente;
import com.delivey.di.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtivacaoClienteService {

    @Autowired
    private List<Notificador> notificador;

    public void ativar(Cliente cliente) {
        cliente.ativar();
        notificador.forEach(n-> n.notificar(cliente, "Cliente ativo com sucesso!"));
    }

}
