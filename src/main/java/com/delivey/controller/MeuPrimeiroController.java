package com.delivey.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class MeuPrimeiroController {

    @GetMapping
    public String hello() {
        return "Hello world";
    }

}
