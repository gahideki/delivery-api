package com.delivey.di.notificacao;

import com.delivey.di.anotacao.TipoDoNotificador;
import com.delivey.di.model.Cliente;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static com.delivey.di.anotacao.NivelUrgencia.NORMAL;

@Profile("dev")
@TipoDoNotificador(NORMAL)
@Component
public class NotificadorEmailMock implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("MOCK Notificando %s através do e-mail %s: %s%n", cliente.getNome(), cliente.getEmail(), mensagem);
    }

}
