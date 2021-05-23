package com.delivey.domain.service;

import com.delivey.domain.exception.EntidadeNaoEncontradaException;
import com.delivey.domain.model.Cozinha;
import com.delivey.domain.model.Restaurante;
import com.delivey.domain.repository.CozinhaRepository;
import com.delivey.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        try {
            Cozinha cozinha = cozinhaRepository.buscarPor(cozinhaId);
            restaurante.setCozinha(cozinha);
            return restauranteRepository.salvar(restaurante);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(String.format("Cozinha de código %d não encontrada", cozinhaId));
        }
    }

    public Restaurante buscarPor(Long id) {
        try {
            return restauranteRepository.buscarPor(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante de código %d não foi encontrado", id));
        }
    }

    public void remover(Long id) {
        try {
            restauranteRepository.remover(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante de código %d não foi encontrado", id));
        }
    }

}
