package com.delivey.api.controller;

import com.delivey.domain.model.Cozinha;
import com.delivey.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping("cozinhas/por-nome")
    public List<Cozinha> buscarTodasPorNome(@RequestParam String nome) {
        return cozinhaRepository.findQualquerCoisaByNome(nome);
    }

    @GetMapping("/cozinhas/unica-por-nome")
    public Optional<Cozinha> buscarUnicaPorNome(String nome) {
        return cozinhaRepository.findByNome(nome);
    }

}
