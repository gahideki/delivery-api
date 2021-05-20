package com.delivey.di.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class Produto {

    private String nome;
    private BigDecimal valorTotal;

}
