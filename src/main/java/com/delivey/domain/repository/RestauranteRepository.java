package com.delivey.domain.repository;

import com.delivey.domain.model.Restaurante;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository {

    List<Restaurante> listar();
    Restaurante salvar(Restaurante restaurante);
    Restaurante buscarPor(Long id);
    void remover(Long id);

}
