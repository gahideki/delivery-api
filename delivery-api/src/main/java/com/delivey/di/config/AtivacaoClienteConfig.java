package com.delivey.di.config;

import com.delivey.di.notificacao.Notificador;
import com.delivey.di.service.AtivacaoClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtivacaoClienteConfig {

    @Bean
    public AtivacaoClienteService ativacaoClienteService(Notificador notificador) {
        return new AtivacaoClienteService(notificador);
    }

}
