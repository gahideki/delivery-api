package com.delivey.api.controller;

import com.delivey.domain.exception.EntidadeEmUsoException;
import com.delivey.domain.exception.EntidadeNaoEncontradaException;
import com.delivey.domain.model.Estado;
import com.delivey.domain.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estados")
public class EstadoController {

    private final EstadoService estadoService;

    @GetMapping
    public List<Estado> listar() {
        return estadoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> buscar(@PathVariable final Long id) {
        try {
            Estado estado = estadoService.buscarPor(id);
            return ResponseEntity.ok(estado);
        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Estado adicionar(@RequestBody Estado estado) {
        return estadoService.salvar(estado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> atualizar(@PathVariable final Long id, @RequestBody Estado estadoInput) {
        Estado estado = estadoService.buscarPor(id);

        if (ObjectUtils.isEmpty(estado))
            return ResponseEntity.notFound().build();

        BeanUtils.copyProperties(estadoInput, estado, "id");
        return ResponseEntity.ok(estadoService.salvar(estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable final Long id) {
        try {
            estadoService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
