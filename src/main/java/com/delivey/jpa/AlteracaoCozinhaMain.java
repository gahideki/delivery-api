package com.delivey.jpa;

import com.delivey.DeliveryApiApplication;
import com.delivey.domain.model.Cozinha;
import com.delivey.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class AlteracaoCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryApiApplication.class)
                                                    .web(WebApplicationType.NONE)
                                                    .run(args);

        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);
        cozinha.setNome("Brasileira");
        cozinha = cozinhaRepository.salvar(cozinha);

        List<Cozinha> cozinhas = cozinhaRepository.listar();
        cozinhas.forEach(c -> System.out.println(c.getNome()));
    }

}
