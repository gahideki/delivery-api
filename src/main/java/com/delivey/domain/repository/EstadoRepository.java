package com.delivey.domain.repository;

import com.delivey.domain.model.Estado;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository {

    List<Estado> listar();
    Estado salvar(Estado estado);
    Estado buscarPor(Long id);
    void remover(Long id);

}
