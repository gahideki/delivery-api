package com.delivey.domain.service;

import com.delivey.domain.exception.EntidadeNaoEncontradaException;
import com.delivey.domain.model.Cidade;
import com.delivey.domain.model.Estado;
import com.delivey.domain.repository.CidadeRepository;
import com.delivey.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.findById(estadoId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Estado de código %d não encontrado", estadoId)));
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public Cidade buscarPor(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Cidade de código %d não foi encontrado", id)));
    }

    public void remover(Long id) {
        try {
            cidadeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(String.format("Cidade de código %d não foi encontrado", id));
        }
    }

}
