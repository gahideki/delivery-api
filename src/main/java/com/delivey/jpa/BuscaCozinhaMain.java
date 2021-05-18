package com.delivey.jpa;

import com.delivey.DeliveryApiApplication;
import com.delivey.domain.model.Cozinha;
import com.delivey.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryApiApplication.class)
                                                    .web(WebApplicationType.NONE)
                                                    .run(args);

        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
        Cozinha cozinha = cozinhaRepository.buscarPor(1L);
        System.out.println(cozinha.getNome());
    }


}
