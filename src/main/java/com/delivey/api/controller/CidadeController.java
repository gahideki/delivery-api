package com.delivey.api.controller;

import com.delivey.api.exceptionhandler.Problema;
import com.delivey.domain.exception.EntidadeNaoEncontradaException;
import com.delivey.domain.exception.EstadoNaoEncontradoException;
import com.delivey.domain.exception.NegocioException;
import com.delivey.domain.model.Cidade;
import com.delivey.domain.service.CidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
        try {
            return cidadeService.salvar(cidade);
        } catch (EstadoNaoEncontradoException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @PutMapping("/{id}")
    public Cidade atualizar(@PathVariable final Long id, @RequestBody Cidade cidadeInput) {
        Cidade cidade = cidadeService.buscarPor(id);
        BeanUtils.copyProperties(cidadeInput, cidade, "id");
        try {
            return cidadeService.salvar(cidade);
        } catch (EstadoNaoEncontradoException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable final Long id) {
        cidadeService.remover(id);
    }

}
