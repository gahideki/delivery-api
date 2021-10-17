package com.delivey.api.controller;

import com.delivey.domain.model.Cidade;
import com.delivey.domain.service.CidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private final CidadeService cidadeService;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeService.listar();
    }

    @GetMapping("/{id}")
    public Cidade buscar(@PathVariable final Long id) {
        return cidadeService.buscarPor(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cidade adicionar(@RequestBody Cidade cidade) {
        return cidadeService.salvar(cidade);
    }

    @PutMapping("/{id}")
    public Cidade atualizar(@PathVariable final Long id, @RequestBody Cidade cidadeInput) {
        Cidade cidade = cidadeService.buscarPor(id);
        BeanUtils.copyProperties(cidadeInput, cidade, "id");
        return cidadeService.salvar(cidade);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable final Long id) {
        cidadeService.remover(id);
    }

}
