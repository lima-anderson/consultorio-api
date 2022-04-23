package com.deps.consultorioapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deps.consultorioapi.model.Paciente;
import com.deps.consultorioapi.repositories.PacienteRepository;
import com.deps.consultorioapi.services.excecoes.ObjetoNaoEncontradoException;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;

	public List<Paciente> buscarTodos() {
		return repo.findAll();
	}
	
	public Paciente buscarPorId(Long id) {
		Optional<Paciente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Paciente.class.getName()));
	}
		
	public Paciente inserir(Paciente obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Paciente alterar(Paciente obj) {
		buscarPorId(obj.getId());
		return repo.save(obj);
	}

	public void apagar(Long id) {
		buscarPorId(id);
		repo.deleteById(id);
	}

}
