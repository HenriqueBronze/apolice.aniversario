package com.br.vida.api.srv.apolice.aniversario.exceptions;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date timestamp;
    private String message;
    private String details;
    private Map<String, String> errors;

    public ExceptionResponse(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.errors = new HashMap<>();
    }

    public ExceptionResponse(Date timestamp, String message, String details, Map<String, String> errors) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

}
