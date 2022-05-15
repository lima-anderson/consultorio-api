package com.deps.consultorioapi.services.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ErroInternoException extends RuntimeException{

    public ErroInternoException(String mensagem){
        super(mensagem);
    }
}
