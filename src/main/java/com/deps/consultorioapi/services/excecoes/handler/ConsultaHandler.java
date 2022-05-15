package com.deps.consultorioapi.services.excecoes.handler;

public class ConsultaHandler {

    private String mensagem;

    public ConsultaHandler(String mensagem){
        this.mensagem = mensagem;
    }

    public String getMensagem(){
        return mensagem;
    }
}
