package com.delivey.api.controller;

import com.delivey.domain.exception.CozinhaNaoEncontradaException;
import com.delivey.domain.exception.NegocioException;
import com.delivey.domain.exception.ValidacaoException;
import com.delivey.domain.model.Endereco;
import com.delivey.domain.model.Restaurante;
import com.delivey.domain.service.RestauranteService;
import com.delivey.utils.ObjectMerger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    private final SmartValidator validator;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteService.listar();
    }

    @GetMapping("/{id}")
    public Restaurante buscar(@PathVariable final Long id) {
        return restauranteService.buscarPor(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Restaurante adicionar(@Valid @RequestBody Restaurante restaurante) {
        getEnderecoViaCEP(restaurante);
        try {
            return restauranteService.salvar(restaurante);
        } catch (CozinhaNaoEncontradaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Restaurante atualizar(@PathVariable final Long id, @Valid @RequestBody Restaurante restauranteInput) {
        Restaurante restaurante = restauranteService.buscarPor(id);
        //getEnderecoViaCEP(restauranteInput);
        BeanUtils.copyProperties(restauranteInput, restaurante, "id", "formasDePagamentos", "endereco", "dataCadastro", "produtos");
        try {
            return restauranteService.salvar(restaurante);
        } catch (CozinhaNaoEncontradaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    private void getEnderecoViaCEP(Restaurante restauranteInput) {
        if (!ObjectUtils.isEmpty(restauranteInput.getEndereco())) {
            Endereco endereco = restauranteService.getEnderecoViaCEP(restauranteInput);
            restauranteInput.setEndereco(endereco);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable final Long id) {
        restauranteService.remover(id);
    }

    @PatchMapping("/{id}")
    public Restaurante atualizarParcial(@PathVariable final Long id, @RequestBody Map<String, Object> campos, HttpServletRequest request) {
        Restaurante restaurante = restauranteService.buscarPor(id);
        ObjectMerger.of(Restaurante.class).mergeRequestBodyToGenericObject(campos, restaurante, request);
        validate(restaurante, "restaurante");
        return atualizar(id, restaurante);
    }

    private void validate(Restaurante restaurante, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
        validator.validate(restaurante, bindingResult);

        if (bindingResult.hasErrors())
            throw new ValidacaoException(bindingResult);
    }

}
