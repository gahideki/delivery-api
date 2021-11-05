package com.delivey.api.exceptionhandler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ProblemaTypeEnum {

    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensivel", HttpStatus.BAD_REQUEST),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso", HttpStatus.CONFLICT),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio", HttpStatus.BAD_REQUEST),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido", HttpStatus.BAD_REQUEST),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado", HttpStatus.NOT_FOUND),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema", HttpStatus.INTERNAL_SERVER_ERROR),
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos", HttpStatus.BAD_REQUEST);

    private String title;
    private String uri;
    private HttpStatus status;

    ProblemaTypeEnum(String title, String path, HttpStatus status) {
        this.uri = "https://algafood.com.br/" + path;
        this.title = title;
        this.status = status;
    }

}
