package com.delivey.domain.service;

import com.delivey.domain.exception.EntidadeEmUsoException;
import com.delivey.domain.exception.EntidadeNaoEncontradaException;
import com.delivey.domain.model.Estado;
import com.delivey.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado buscarPor(Long id) {
        return estadoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Estado de código %d não foi encontrado", id)));
    }

    public void remover(Long id) {
        try {
            estadoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(String.format("Estado de código %d não foi encontrado", id));
        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(String.format("Estado de código %d não pode ser removida, pois está em uso", id));
        }
    }

}
