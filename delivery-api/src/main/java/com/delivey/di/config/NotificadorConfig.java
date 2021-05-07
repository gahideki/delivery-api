package com.delivey.di.config;

import com.delivey.di.notificacao.NotificadorEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificadorConfig {

    @Bean
    public NotificadorEmail notificadorEmail() {
        NotificadorEmail notificadorEmail = new NotificadorEmail("smtp.algamail.com.br");
        notificadorEmail.setCaixaAlta(true);
        return notificadorEmail;
    }

}
