package br.com.estudos.notificationpattern.exception;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception{

    private static final long serialVersionUID = 1L;

    private List<String> messages = new ArrayList<>();

    private Integer code;

    public BusinessException() {
        super();
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(List<String> messages) {
        super();
        this.messages = messages;
    }

    public BusinessException(String message) {
        super(message);
        this.messages.add(message);
    }

    public BusinessException(List<String> messages, Integer code) {
        super();
        this.messages = messages;
        this.code = code;
    }

    public BusinessException(String message, Integer code) {
        super(message);
        this.messages.add(message);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.messages.add(message);
    }

    public BusinessException(Throwable cause, List<String> messages) {
        super(cause);
        this.messages = messages;
    }

    public BusinessException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.messages.add(message);
        this.code = code;
    }

    public BusinessException(Throwable cause, List<String> messages, Integer code) {
        super(cause);
        this.messages = messages;
        this.code = code;
    }

    public List<String> getMessages() {
        return messages;
    }

    public Integer getCode() {
        return code;
    }

}
