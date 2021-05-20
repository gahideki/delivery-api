package com.delivey.di.controller;

import com.delivey.di.model.Cliente;
import com.delivey.di.model.Produto;
import com.delivey.di.service.AtivacaoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    private AtivacaoClienteService ativacaoClienteService;

    @GetMapping
    public void main() {
        Cliente joao = new Cliente("joão", "joao@gmail.com", null);
        Cliente maria = new Cliente("maria", "maria@gmail.com", "1234-5678");

        Produto ps4 = new Produto("PS4", BigDecimal.valueOf(3800));
        Produto xbox = new Produto("XBox", BigDecimal.valueOf(2800));

        ativacaoClienteService.ativar(joao, ps4);
        ativacaoClienteService.ativar(maria, xbox);
    }

}
