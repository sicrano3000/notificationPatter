package br.com.estudos.notificationpattern.exception;

import br.com.estudos.notificationpattern.model.dto.Response;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;

@Log4j2
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> defaultErrorHandler(HttpServletRequest req, Exception ex){
        return responseErro(ex, ex instanceof EntityNotFoundException ? HttpStatus.NOT_FOUND : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Response<String>> businessExceptionHandler(final BusinessException ex, final WebRequest request){
        return responseErro(ex, HttpStatus.BAD_REQUEST);
    }

    private String getMessage(Exception erro) {
        String message = (!(erro instanceof BusinessException) ? "Ocorreu um erro inesperado: " : "") + erro.getMessage();
        if (erro.getCause()!=null) {
            message = message + "; Causa: " + ExceptionUtils.getRootCauseMessage(erro);
        }
        return message;
    }

    private ResponseEntity<Response<String>> responseErro(Exception erro, HttpStatus status) {
        log.error("[" + erro.getClass().getName() + "]", erro);
        return responseErro(getMessage(erro), status);
    }

    private ResponseEntity<Response<String>> responseErro(String mensagem, HttpStatus status) {
        Response<String> response = new Response<>("error", status.value(), Arrays.asList(mensagem), null);
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

}
