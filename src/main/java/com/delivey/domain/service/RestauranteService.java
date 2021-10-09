package com.delivey.domain.service;

import com.delivey.domain.exception.EntidadeNaoEncontradaException;
import com.delivey.domain.model.Cozinha;
import com.delivey.domain.model.Endereco;
import com.delivey.domain.model.Restaurante;
import com.delivey.domain.repository.CozinhaRepository;
import com.delivey.domain.repository.RestauranteRepository;
import com.delivey.infra.feign.EnderecoFeign;
import com.delivey.infra.feign.dto.EnderecoFeignDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    private final CozinhaRepository cozinhaRepository;

    private final EnderecoFeign enderecoFeign;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Cozinha de código %d não foi encontrada", cozinhaId)));
        EnderecoFeignDTO enderecoViaCEP = enderecoFeign.getEnderecoViaCEP(restaurante.getEndereco().getCep());
        Endereco endereco = enderecoViaCEP.convertToEnderecoEntity();

        restaurante.setCozinha(cozinha);
        restaurante.setEndereco(endereco);
        return restauranteRepository.save(restaurante);
    }

    public Restaurante buscarPor(Long id) {
        return restauranteRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Restaurante de código %d não foi encontrado", id)));
    }

    public void remover(Long id) {
        try {
            restauranteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante de código %d não foi encontrado", id));
        }
    }

}
