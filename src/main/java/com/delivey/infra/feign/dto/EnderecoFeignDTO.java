package com.delivey.infra.feign.dto;

import com.delivey.domain.model.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoFeignDTO {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    private static Endereco convertToEnderecoEntity(EnderecoFeignDTO enderecoFeignDTO) {
        var endereco = new Endereco();
        endereco.setCep(enderecoFeignDTO.getCep());
        endereco.setLogradouro(enderecoFeignDTO.getLogradouro());
        endereco.setComplemento(enderecoFeignDTO.getComplemento());
        endereco.setBairro(enderecoFeignDTO.getBairro());
        endereco.getCidade().setNome(enderecoFeignDTO.getLocalidade());
        endereco.getCidade().getEstado().setNome(enderecoFeignDTO.getUf());
        return endereco;
    }

}
