package com.delivey.di.controller;

import com.delivey.di.model.Cliente;
import com.delivey.di.service.AtivacaoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    private AtivacaoClienteService ativacaoClienteService;

    @GetMapping
    public void main() {
        Cliente joao = new Cliente("joão", "joao@gmail.com", "1234-5678");
        Cliente maria = new Cliente("maria", "maria@gmail.com", "1234-5678");

        ativacaoClienteService.ativar(joao);
        ativacaoClienteService.ativar(maria);
    }

}
