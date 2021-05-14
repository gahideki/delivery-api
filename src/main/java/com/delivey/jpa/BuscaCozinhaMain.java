package com.delivey.jpa;

import com.delivey.DeliveryApiApplication;
import com.delivey.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class BuscaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryApiApplication.class)
                                                    .web(WebApplicationType.NONE)
                                                    .run(args);

        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
        Cozinha cozinha = cadastroCozinha.buscarPor(1L);
        System.out.println(cozinha.getNome());
    }


}
