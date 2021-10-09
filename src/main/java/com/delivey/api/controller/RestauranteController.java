package com.delivey.api.controller;

import com.delivey.domain.exception.EntidadeNaoEncontradaException;
import com.delivey.domain.model.Endereco;
import com.delivey.domain.model.Restaurante;
import com.delivey.domain.service.RestauranteService;
import com.delivey.utils.ObjectMerger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable final Long id) {
        try {
            Restaurante restaurante = restauranteService.buscarPor(id);
            return ResponseEntity.ok(restaurante);
        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            getEnderecoViaCEP(restaurante);
            restaurante = restauranteService.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable final Long id, @RequestBody Restaurante restauranteInput) {
        try {
            Restaurante restaurante = restauranteService.buscarPor(id);
            getEnderecoViaCEP(restauranteInput);
            BeanUtils.copyProperties(restauranteInput, restaurante, "id", "formasDePagamentos", "endereco", "dataCadastro", "produtos");
            return ResponseEntity.ok(restauranteService.salvar(restaurante));
        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    private void getEnderecoViaCEP(Restaurante restauranteInput) {
        if (!ObjectUtils.isEmpty(restauranteInput.getEndereco())) {
            Endereco endereco = restauranteService.getEnderecoViaCEP(restauranteInput);
            restauranteInput.setEndereco(endereco);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable final Long id) {
        try {
            restauranteService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable final Long id, @RequestBody Map<String, Object> campos) {
        try {
            Restaurante restaurante = restauranteService.buscarPor(id);
            ObjectMerger.of(Restaurante.class).mergeRequestBodyToGenericObject(campos, restaurante);
            return atualizar(id, restaurante);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
