package com.delivey.jpa;

import com.delivey.DeliveryApiApplication;
import com.delivey.domain.model.Restaurante;
import com.delivey.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaRestauranteMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryApiApplication.class)
                                                    .web(WebApplicationType.NONE)
                                                    .run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
        List<Restaurante> restaurantes = restauranteRepository.findAll();
        restaurantes.forEach(restaurante -> System.out.printf("%s - %f - %s\n", restaurante.getNome(),
                                                                                restaurante.getTaxaFrete(),
                                                                                restaurante.getCozinha().getNome()));
    }

}
