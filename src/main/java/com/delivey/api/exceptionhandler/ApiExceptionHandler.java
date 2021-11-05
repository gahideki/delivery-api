package com.delivey.api.exceptionhandler;

import com.delivey.domain.exception.EntidadeEmUsoException;
import com.delivey.domain.exception.EntidadeNaoEncontradaException;
import com.delivey.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema"
            + " persistir, entre em contato com o administrador do sistema.";

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
        ProblemaTypeEnum problemaTypeEnum = ProblemaTypeEnum.RECURSO_NAO_ENCONTRADO;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleIllegalArgumentException(Exception ex, WebRequest request) {
        ProblemaTypeEnum problemaTypeEnum = ProblemaTypeEnum.ERRO_DE_SISTEMA;
        String detail = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato "
                + "com o administrador do sistema.";

        // Importante o printStackTrace (pelo menos por enquanto, que não estou fazendo logging)
        // para mostrar a stacktrace no console. Se não fizer isso, não vou ver a stacktrace
        // de exceptions que seriam importantes durante, especialmente na fase de desenvolvimento
        ex.printStackTrace();
        Problema problema = builderProblema(problemaTypeEnum.getStatus(), problemaTypeEnum, detail).build();

        return handleExceptionInternal(ex, problema, new HttpHeaders(), problemaTypeEnum.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemaTypeEnum problemType = ProblemaTypeEnum.RECURSO_NAO_ENCONTRADO;
        String detail = String.format("O recurso '%s' que você tentou acessar é inexistente", ex.getRequestURL());
        Problema problema = builderProblema(problemType.getStatus(), problemType, detail).build();

        return handleExceptionInternal(ex, problema, headers, problemType.getStatus(), request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpHeaders headers, WebRequest request) {
        ProblemaTypeEnum problemType = ProblemaTypeEnum.PARAMETRO_INVALIDO;
        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s' que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s",
                                      ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
        Problema problema = builderProblema(problemType.getStatus(), problemType, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

        return handleExceptionInternal(ex, problema, headers, problemType.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBinding((PropertyBindingException) rootCause, headers, request);
        }

        ProblemaTypeEnum problemaTypeEnum = ProblemaTypeEnum.MENSAGEM_INCOMPREENSIVEL;
        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe";
        Problema problema = builderProblema(problemaTypeEnum.getStatus(), problemaTypeEnum, detail).build();

        return handleExceptionInternal(ex, problema, new HttpHeaders(), problemaTypeEnum.getStatus(), request);
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = joinPath(ex.getPath());
        ProblemaTypeEnum problemaTypeEnum = ProblemaTypeEnum.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s' que é de um tipo inválido. " +
                "Corrija e informe um valor compátivel com o tipo %s", path, ex.getValue(), ex.getTargetType().getSimpleName());
        Problema problema = builderProblema(status, problemaTypeEnum, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex, HttpHeaders headers, WebRequest request) {
        // Criei o método joinPath para reaproveitar em todos os métodos que precisam concatenar os nomes das propriedades (separando por ".")
        String path = joinPath(ex.getPath());
        ProblemaTypeEnum problemType = ProblemaTypeEnum.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("A propriedade '%s' não existe. Corrija ou remova essa propriedade e tente novamente", path);
        Problema problema = builderProblema(problemType.getStatus(), problemType, detail).build();

        return handleExceptionInternal(ex, problema, headers, problemType.getStatus(), request);
    }

    private String joinPath(List<Reference> references) {
        return references.stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ObjectUtils.isEmpty(body)) {
            body = Problema.builder().title(status.getReasonPhrase()).status(status.value()).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL).build();
        } else if (body instanceof String) {
            body = Problema.builder().title((String) body).status(status.value()).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL).build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problema.ProblemaBuilder builderProblema(HttpStatus status, ProblemaTypeEnum problemaTypeEnum, String detail) {
        return Problema.builder().status(status.value()).type(problemaTypeEnum.getUri()).title(problemaTypeEnum.getTitle()).detail(detail);
    }

}
