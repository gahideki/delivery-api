package com.delivey.jpa;

import com.delivey.DeliveryApiApplication;
import com.delivey.domain.model.Cozinha;
import com.delivey.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ExclusaoCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryApiApplication.class)
                                                    .web(WebApplicationType.NONE)
                                                    .run(args);

        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
        cozinhaRepository.deleteById(1L);
        List<Cozinha> cozinhas = cozinhaRepository.findAll();
        cozinhas.forEach(cozinha -> System.out.println(cozinha.getNome()));
    }


}
