package com.delivey.api.exceptionhandler;

import com.delivey.domain.exception.EntidadeEmUsoException;
import com.delivey.domain.exception.EntidadeNaoEncontradaException;
import com.delivey.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
        ProblemaTypeEnum problemaTypeEnum = ProblemaTypeEnum.ENTIDADE_NAO_ENCONTRADA;
        String detail = ex.getMessage();
        Problema problema = builderProblema(problemaTypeEnum.getStatus(), problemaTypeEnum, detail).build();
        return handleExceptionInternal(ex, problema, new HttpHeaders(), problemaTypeEnum.getStatus(), request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {
        ProblemaTypeEnum problemaTypeEnum = ProblemaTypeEnum.ERRO_NEGOCIO;
        String detail = ex.getMessage();
        Problema problema = builderProblema(problemaTypeEnum.getStatus(), problemaTypeEnum, detail).build();
        return handleExceptionInternal(ex, problema, new HttpHeaders(), problemaTypeEnum.getStatus(), request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {
        ProblemaTypeEnum problemaTypeEnum = ProblemaTypeEnum.ENTIDADE_EM_USO;
        String detail = ex.getMessage();
        Problema problema = builderProblema(problemaTypeEnum.getStatus(), problemaTypeEnum, detail).build();
        return handleExceptionInternal(ex, problema, new HttpHeaders(), problemaTypeEnum.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ObjectUtils.isEmpty(body)) {
            body = Problema.builder().title(status.getReasonPhrase()).status(status.value()).build();
        } else if (body instanceof String) {
            body = Problema.builder().title((String) body).status(status.value()).build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problema.ProblemaBuilder builderProblema(HttpStatus status, ProblemaTypeEnum problemaTypeEnum, String detail) {
        return Problema.builder().status(status.value()).type(problemaTypeEnum.getUri()).title(problemaTypeEnum.getTitle()).detail(detail);
    }

}
