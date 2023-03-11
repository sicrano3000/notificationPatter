package br.com.estudos.notificationpattern.controller;

import br.com.estudos.notificationpattern.model.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

public abstract class BaseController {

    public ResponseEntity<?> responseEntity(HttpStatus status, Object obj) {
        var body = new Response().builder()
                .status(status.getReasonPhrase())
                .code(status.value())
                .messages(null)
                .result(Arrays.asList(obj))
                .build();

        return ResponseEntity.status(status).body(body);
    }

}
