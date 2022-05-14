package com.deps.consultorioapi.services.excecoes;

public class IntegridadeDeDadoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IntegridadeDeDadoException(String msg) {
		super(msg);
	}

	public IntegridadeDeDadoException(String msg, Throwable cause) {
		super(msg, cause);
	}

}