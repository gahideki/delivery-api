package com.delivey.api.controller;

import com.delivey.domain.model.Cozinha;
import com.delivey.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository repository;

    @GetMapping
    public List<Cozinha> listar() {
        return repository.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        Cozinha cozinha = repository.buscarPor(id);

        if (cozinha == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cozinha);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cozinha inserir(@RequestBody Cozinha cozinha) {
        return repository.salvar(cozinha);
    }

}
