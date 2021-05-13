package com.delivey.di.notificacao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificador.email")
@Getter
@Setter
public class NotificadorProperties {

    /**
     * Host do servidor de e-mail
     */
    private String host;

    /**
     * Porta do servidor de e-mail
     */
    private Integer porta;

}
