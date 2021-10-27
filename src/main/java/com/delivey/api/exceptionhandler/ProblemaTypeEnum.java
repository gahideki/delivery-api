package com.delivey.api.exceptionhandler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ProblemaTypeEnum {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada", HttpStatus.NOT_FOUND);

    private String title;
    private String uri;
    private HttpStatus status;

    ProblemaTypeEnum(String title, String path, HttpStatus status) {
        this.uri = "https://algafood.com.br/" + path;
        this.title = title;
        this.status = status;
    }

}
