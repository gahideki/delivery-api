package com.delivey.api.controller;

import com.delivey.domain.exception.EntidadeNaoEncontradaException;
import com.delivey.domain.model.Restaurante;
import com.delivey.domain.service.RestauranteService;
import com.delivey.utils.ObjectMerger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        Restaurante restaurante = restauranteService.buscarPor(id);
        return ObjectUtils.isEmpty(restaurante) ? ResponseEntity.notFound().build() : ResponseEntity.ok(restaurante);
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = restauranteService.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restauranteInput) {
        try {
            Restaurante restaurante = restauranteService.buscarPor(id);
            BeanUtils.copyProperties(restauranteInput, restaurante, "id");
            return ResponseEntity.ok(restauranteService.salvar(restaurante));
        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            restauranteService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        try {
            Restaurante restaurante = restauranteService.buscarPor(id);
            ObjectMerger.of(Restaurante.class).mergeRequestBodyToGenericObject(campos, restaurante);
            return atualizar(id, restaurante);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
