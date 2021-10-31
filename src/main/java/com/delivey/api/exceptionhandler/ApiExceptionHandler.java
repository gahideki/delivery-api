package com.delivey.api.exceptionhandler;

import com.delivey.domain.exception.EntidadeEmUsoException;
import com.delivey.domain.exception.EntidadeNaoEncontradaException;
import com.delivey.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

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
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if(rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        }

        ProblemaTypeEnum problemaTypeEnum = ProblemaTypeEnum.MENSAGEM_INCOMPREENSIVEL;
        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe";
        Problema problema = builderProblema(problemaTypeEnum.getStatus(), problemaTypeEnum, detail).build();
        return handleExceptionInternal(ex, problema, new HttpHeaders(), problemaTypeEnum.getStatus(), request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
        ProblemaTypeEnum problemaTypeEnum = ProblemaTypeEnum.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s' que é de um tipo inválido. " +
                "Corrija e informe um valor compátivel com o tipo %s",path , ex.getValue(), ex.getTargetType().getSimpleName());
        Problema problema = builderProblema(status, problemaTypeEnum, detail).build();
        return handleExceptionInternal(ex, problema, headers, status, request);
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
