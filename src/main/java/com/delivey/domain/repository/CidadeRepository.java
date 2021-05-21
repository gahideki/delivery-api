package com.delivey.domain.repository;

import com.delivey.domain.model.Cidade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository {

    List<Cidade> listar();
    Cidade salvar(Cidade cidade);
    Cidade buscarPor(Long id);
    void remover(Long id);

}
