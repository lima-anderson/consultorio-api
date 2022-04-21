package com.deps.consultorioapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deps.consultorioapi.model.Paciente;
import com.deps.consultorioapi.services.PacienteService;

@RestController
@RequestMapping(value = "/pacientes")
public class PacienteController {
	
	@Autowired
	private PacienteService paciente;

	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Paciente>> buscarTodos() {
		List<Paciente> lista = paciente.buscarTodos();
		return ResponseEntity.ok().body(lista);
	}
}
