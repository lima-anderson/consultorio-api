package com.deps.consultorioapi.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.deps.consultorioapi.model.Usuario;
import com.deps.consultorioapi.services.UsuarioService;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioService usuario;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> buscarTodos() {
		List<Usuario> lista = usuario.buscarTodos();
		return ResponseEntity.ok().body(lista);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		Usuario obj = usuario.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Usuario obj) {
		obj = usuario.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@PathVariable Long id, @RequestBody Usuario obj) {
		obj.setId(id);
		obj = usuario.alterar(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> apagar(@PathVariable Long id) {
		usuario.apagar(id);
		return ResponseEntity.noContent().build();
	}
}
