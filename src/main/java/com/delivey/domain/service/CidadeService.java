package com.delivey.domain.service;

import com.delivey.domain.exception.EntidadeNaoEncontradaException;
import com.delivey.domain.model.Cidade;
import com.delivey.domain.model.Estado;
import com.delivey.domain.repository.CidadeRepository;
import com.delivey.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CidadeService {

    private static final String MSG_COZINHA_EM_USO = "Cidade de código %d não foi encontrado";

    private final CidadeRepository cidadeRepository;

    private final EstadoRepository estadoRepository;

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.buscarOuFalhar(estadoId);
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public Cidade buscarPor(Long id) {
        return cidadeRepository.buscarOuFalhar(id);
    }

    public void remover(Long id) {
        try {
            cidadeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_EM_USO, id));
        }
    }

}
