package com.delivey.infra.feign.dto;

import com.delivey.domain.model.Cidade;
import com.delivey.domain.model.Endereco;
import com.delivey.domain.model.Estado;
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

    public Endereco convertToEnderecoEntity() {
        var endereco = new Endereco();
        var cidade = new Cidade();
        var estado = new Estado();

        cidade.setEstado(estado);
        endereco.setCidade(cidade);

        endereco.setCep(this.getCep());
        endereco.setLogradouro(this.getLogradouro());
        endereco.setComplemento(this.getComplemento());
        endereco.setBairro(this.getBairro());
        endereco.getCidade().setNome(this.getLocalidade());
        endereco.getCidade().getEstado().setNome(this.getUf());
        return endereco;
    }

}
