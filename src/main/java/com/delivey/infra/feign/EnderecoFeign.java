package com.delivey.infra.feign;

import com.delivey.infra.feign.dto.EnderecoFeignDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "endereco", url = "https://viacep.com.br/ws")
public interface EnderecoFeign {

    @GetMapping("/{cep}/json")
    EnderecoFeignDTO getEnderecoViaCEP(@PathVariable String cep);

}
