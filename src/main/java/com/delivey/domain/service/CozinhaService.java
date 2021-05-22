package com.delivey.domain.service;

import com.delivey.domain.model.Cozinha;
import com.delivey.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }

    public Cozinha buscarPor(Long id) {
        return cozinhaRepository.buscarPor(id);
    }

    public void remover(Long id) {
        cozinhaRepository.remover(id);
    }

}
