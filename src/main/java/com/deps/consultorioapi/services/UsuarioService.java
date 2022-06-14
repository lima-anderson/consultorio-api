package com.deps.consultorioapi.services;

import java.util.List;
import java.util.Optional;

import com.deps.consultorioapi.model.DTO.UsuarioRequestDTO;
import com.deps.consultorioapi.model.Role;
import com.deps.consultorioapi.repositories.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.deps.consultorioapi.model.Usuario;
import com.deps.consultorioapi.repositories.UsuarioRepository;
import com.deps.consultorioapi.services.excecoes.IntegridadeDeDadoException;
import com.deps.consultorioapi.services.excecoes.ObjetoNaoEncontradoException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	RoleRepository roleRepository;

	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}

	public Usuario buscarPorId(Long id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

//	public Usuario inserir(Usuario obj) {
//		obj.setId(null);
//		return usuarioRepository.save(obj);
//	}

	public Usuario inserir(UsuarioRequestDTO usuarioRequestDTO){
		logger.info("NOME: " + usuarioRequestDTO.getNome() + " USERNAME: " + usuarioRequestDTO.getUsername());

		String nome = usuarioRequestDTO.getNome();
		String username = usuarioRequestDTO.getUsername();
		String password = new BCryptPasswordEncoder().encode(usuarioRequestDTO.getPassword());
		Usuario usuario = new Usuario(username, password, nome);

		// Pegando o username do funcionario logado na sessão
		String funcionario =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		usuario.setCadastradoPor(funcionario);

		Role role_operador = roleRepository.findById(1L).get();
		Role role_admin = roleRepository.findById(2L).get();


		if (usuarioRequestDTO.getAdmin()){
			usuario.addRole(role_operador);
			usuario.addRole(role_admin);

			return usuarioRepository.save(usuario);
		}

		usuario.addRole(role_operador);
		return usuarioRepository.save(usuario);
	}

	public Usuario alterar(Long idUsuario, UsuarioRequestDTO usuarioRequestDTO) {
		Usuario usuario = usuarioRepository.findById(idUsuario).get();

		usuario.setUsername(usuarioRequestDTO.getUsername());
		usuario.setNome(usuarioRequestDTO.getNome());

		Role role_admin = roleRepository.findById(2L).get();

		if (usuarioRequestDTO.getAdmin() == true && usuario.getAuthorities().size() < 2){
			usuario.addRole(role_admin);
		}

		if (usuarioRequestDTO.getAdmin() == false && usuario.getAuthorities().size() == 2){
			usuario.removeRole(role_admin);
		}

		logger.info("NOME: " + usuario.getNome() + " USERNAME: " + usuario.getUsername());

		return usuarioRepository.save(usuario);

	}

	public void apagar(Long id) {
		buscarPorId(id);
		try {
			usuarioRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new IntegridadeDeDadoException("Não é possível excluir usuário");
		}
	}

//	public Usuario buscarUsuarioPorUsername(String username){
//		return usuarioRepository.findUsuarioByUsername(username);
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findUsuarioByUsername(username);

		if (usuario == null){
			throw new UsernameNotFoundException("Usuário inválido");
		}
		return usuario;
	}
}
