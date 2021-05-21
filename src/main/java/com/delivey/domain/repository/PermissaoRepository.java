package com.delivey.domain.repository;

import com.delivey.domain.model.Permissao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissaoRepository {

    List<Permissao> listar();
    Permissao salvar(Permissao permissao);
    Permissao buscarPor(Long id);
    void remover(Long id);

}
