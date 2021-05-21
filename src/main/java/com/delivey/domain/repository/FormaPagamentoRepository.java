package com.delivey.domain.repository;

import com.delivey.domain.model.FormaPagamento;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormaPagamentoRepository {

    List<FormaPagamento> listar();
    FormaPagamento salvar(FormaPagamento formaPagamento);
    FormaPagamento buscarPor(Long id);
    void remover(Long id);

}
