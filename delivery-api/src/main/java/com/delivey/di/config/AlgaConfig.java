package com.delivey.di.config;

import com.delivey.di.notificacao.NotificadorEmail;
import com.delivey.di.service.AtivacaoClienteService;
import org.springframework.context.annotation.Bean;

//  @Configuration
public class AlgaConfig {

    @Bean
    public NotificadorEmail notificadorEmail() {
        return new NotificadorEmail("smtp.algamail.com.br");
    }

    @Bean
    public AtivacaoClienteService ativacaoClienteService() {
        return new AtivacaoClienteService(notificadorEmail());
    }

}
