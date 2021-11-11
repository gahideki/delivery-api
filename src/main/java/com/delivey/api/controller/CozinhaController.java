package com.delivey.api.controller;

import com.delivey.domain.model.Cozinha;
import com.delivey.domain.service.CozinhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private final CozinhaService cozinhaService;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaService.listar();
    }

    @GetMapping("/{id}")
    public Cozinha buscar(@PathVariable final Long id) {
        return cozinhaService.buscarPor(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cozinhaService.salvar(cozinha);
    }

    @PutMapping("/{id}")
    public Cozinha atualizar(@PathVariable final Long id, @RequestBody Cozinha cozinhaInput) {
        Cozinha cozinha = cozinhaService.buscarPor(id);
        BeanUtils.copyProperties(cozinhaInput, cozinha, "id");
        return cozinhaService.salvar(cozinha);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable final Long id) {
        cozinhaService.remover(id);
    }

}
