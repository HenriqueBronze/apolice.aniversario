package com.br.vida.api.srv.apolice.aniversario.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InternalRequestException(String errorMessage) {
        super(errorMessage);
    }

}