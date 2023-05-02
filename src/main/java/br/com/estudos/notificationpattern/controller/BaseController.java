package br.com.estudos.notificationpattern.controller;

import br.com.estudos.notificationpattern.exception.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public abstract class BaseController {

    /**
     * @param status {@link HttpStatus} to return http status and reason
     * @param result {@link T} the type of return response entity
     * @param <T>    {@link T} the type of return response entity
     * @return {@link ResponseEntity} the type of response that springs handle to create status responses and headers
     * @deprecated because should use especific functions with Headers and abstract the return
     */
    @Deprecated
    public <T> ResponseEntity<Response<T>> response(HttpStatus status, T result) {
        Objects.requireNonNull(status);
        var body = Response.<T>builder()
                .status(status.getReasonPhrase())
                .code(status.value())
                .messages(null)
                .result(result)
                .build();

        return ResponseEntity
                .status(status)
                .body(body);
    }

    protected <T> ResponseEntity<Response<T>> responseOk(T result) {
        var body = Response.<T>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .code(HttpStatus.OK.value())
                .result(result)
                .build();

        return ResponseEntity
                .ok(body);
    }

    protected <T> ResponseEntity<Response<T>> responseCreated(T result) {
        var body = Response.<T>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .code(HttpStatus.CREATED.value())
                .result(result)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(body);
    }

    protected ResponseEntity<Response<Void>> responseNoContent() {
        return ResponseEntity
                .noContent()
                .build();
    }

}
