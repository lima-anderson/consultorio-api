package com.deps.consultorioapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.deps.consultorioapi.model.Usuario;
import com.deps.consultorioapi.repositories.UsuarioRepository;
import com.deps.consultorioapi.services.excecoes.IntegridadeDeDadoException;
import com.deps.consultorioapi.services.excecoes.ObjetoNaoEncontradoException;

public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}

	public Usuario buscarPorId(Long id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

	public Usuario inserir(Usuario obj) {
		obj.setId(null);
		return usuarioRepository.save(obj);
	}

	public Usuario alterar(Usuario obj) {
		buscarPorId(obj.getId());
		return usuarioRepository.save(obj);
	}

	public void apagar(Long id) {
		buscarPorId(id);
		try {
			usuarioRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new IntegridadeDeDadoException("Não é possível excluir usuário");
		}
	}
}
