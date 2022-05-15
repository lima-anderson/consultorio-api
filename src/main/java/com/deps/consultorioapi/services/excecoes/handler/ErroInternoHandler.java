package com.deps.consultorioapi.services.excecoes.handler;

import com.deps.consultorioapi.services.excecoes.ErroInternoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroInternoHandler {

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ErroInternoException.class})
    public ConsultaHandler handler(ErroInternoException exception){
        ConsultaHandler consultaHandler = new ConsultaHandler(exception.getMessage());
        return consultaHandler;
    }
}
