package com.delivey.domain.repository;

import com.delivey.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> listar();
    Estado salvar(Estado estado);
    Estado buscarPor(Long id);
    void remover(Long id);

}
