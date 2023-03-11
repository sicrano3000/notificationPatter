package br.com.estudos.notificationpattern.exception;

import br.com.estudos.notificationpattern.model.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Response> BusinessException(BusinessException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new Response(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value(), Arrays.asList(e.getMessages().toString()), null)
        );
    }

}
