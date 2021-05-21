package com.delivey.domain.repository;

import com.delivey.domain.model.Cozinha;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface CozinhaRepository {

    List<Cozinha> listar();
    Cozinha salvar(Cozinha cozinha);
    Cozinha buscarPor(Long id);
    void remover(Long id);

}
