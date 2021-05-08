package com.delivey.di.event;

import com.delivey.di.model.Cliente;
import com.delivey.di.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
@AllArgsConstructor
public class ClienteAtivadoEvent {

    private Cliente cliente;
    private Produto produto;

    public boolean temTelefone() {
        return !StringUtils.isEmpty(cliente.getTelefone());
    }

}
