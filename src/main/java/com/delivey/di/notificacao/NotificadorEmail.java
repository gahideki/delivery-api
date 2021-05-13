package com.delivey.di.notificacao;

import com.delivey.di.anotacao.TipoDoNotificador;
import com.delivey.di.model.Cliente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static com.delivey.di.anotacao.NivelUrgencia.NORMAL;

@Profile("prod")
@TipoDoNotificador(NORMAL)
@Component
public class NotificadorEmail implements Notificador {

    @Value("${notificador.email.host}")
    private String host;

    @Value("${notificador.email.porta}")
    private Integer porta;

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Host:" + host);
        System.out.println("Porta:" + porta);

        System.out.printf("Notificando %s através do e-mail %s: %s%n", cliente.getNome(), cliente.getEmail(), mensagem);
    }

}
