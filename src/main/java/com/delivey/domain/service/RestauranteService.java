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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestauranteService {

    private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Restaurante de código %d não foi encontrado";

    private final RestauranteRepository restauranteRepository;

    private final CozinhaRepository cozinhaRepository;

    private final EnderecoFeign enderecoFeign;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscarOuFalhar(cozinhaId);
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    public Endereco getEnderecoViaCEP(Restaurante restaurante) {
        EnderecoFeignDTO enderecoViaCEP = enderecoFeign.getEnderecoViaCEP(restaurante.getEndereco().getCep());
        Endereco endereco = enderecoViaCEP.convertToEnderecoEntity();
        return endereco;
    }

    public Restaurante buscarPor(Long id) {
        return restauranteRepository.buscarOuFalhar(id);
    }

    public void remover(Long id) {
        try {
            restauranteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, id));
        }
    }

}
