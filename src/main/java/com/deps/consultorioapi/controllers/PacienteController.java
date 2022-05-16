package com.deps.consultorioapi.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.deps.consultorioapi.model.Paciente;
import com.deps.consultorioapi.services.PacienteService;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
		Paciente obj = paciente.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Paciente obj) {
		obj = paciente.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@PathVariable Long id, @RequestBody Paciente obj) {
		obj.setId(id);
		obj = paciente.alterar(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> apagar(@PathVariable Long id) {
		paciente.apagar(id);
		return ResponseEntity.noContent().build();
	}

}
