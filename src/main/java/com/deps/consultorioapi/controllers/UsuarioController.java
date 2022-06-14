package com.deps.consultorioapi.controllers;

import java.net.URI;
import java.util.List;

import com.deps.consultorioapi.model.DTO.UsuarioRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.deps.consultorioapi.model.Usuario;
import com.deps.consultorioapi.services.UsuarioService;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService usuario;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> buscarTodos() {
		List<Usuario> lista = usuario.buscarTodos();
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		Usuario obj = usuario.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Usuario> inserir(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
		return ResponseEntity.ok(usuario.inserir(usuarioRequestDTO));
	}

	@PutMapping({"/{id}"})
	public ResponseEntity<Usuario> alterar(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
		return ResponseEntity.ok(usuario.alterar(id, usuarioRequestDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> apagar(@PathVariable Long id) {
		usuario.apagar(id);
		return ResponseEntity.noContent().build();
	}


	@GetMapping("/username/{username}")
	public ResponseEntity<UserDetails> buscarUsuarioPorUsername(@PathVariable String username){
		return ResponseEntity.ok(usuario.loadUserByUsername(username));
	}
}
