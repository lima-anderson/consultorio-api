package com.deps.consultorioapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deps.consultorioapi.model.Paciente;
import com.deps.consultorioapi.repositories.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;

	public List<Paciente> buscarTodos() {
		return repo.findAll();
	}

}
