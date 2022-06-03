package com.deps.consultorioapi.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.deps.consultorioapi.model.Especialidade;
import com.deps.consultorioapi.model.Medico;
import com.deps.consultorioapi.repositories.ConsultaRepository;
import com.deps.consultorioapi.repositories.MedicoRepository;
import com.deps.consultorioapi.services.excecoes.ErroInternoException;

@Service
public class MedicoService {

	@Autowired
	MedicoRepository medicoRepository;

	@Autowired
	ConsultaRepository consultaRepository;

	public List<Medico> listarMedicos() {
		return medicoRepository.findAll();
	}

	public Medico buscarMedicoPorId(Long id) {
		Optional<Medico> obj = medicoRepository.findById(id);
		return obj.orElseThrow(() -> new ErroInternoException("Médico não encontrado, por favor verique o ID informado"));
	}

	public Medico cadastrarMedico(Medico medico) {
		return medicoRepository.save(medico);
	}

	public void apagarMedico(Long id) {
		buscarMedicoPorId(id);
		try {
			medicoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new ErroInternoException("Não foi possível fazer a operação com ID informado");
		}
	}

	public Medico atualizarMedico(Long id, Medico medico) {
		Medico medicoAtual = this.buscarMedicoPorId(id);
		if (medicoAtual != null) {
			if (medico.getNome() != null && !medico.getNome().isEmpty()) {
				medicoAtual.setNome(medico.getNome());
			}
			if (medico.getCrm() != null) {
				medicoAtual.setCrm(medico.getCrm());
			}
			if (medico.getEmail() != null && !medico.getEmail().isEmpty()) {
				medicoAtual.setEmail(medico.getEmail());
			}
			if (medico.getEspecialidade() != null && !medico.getEspecialidade().equals("")) {
				medicoAtual.setEspecialidade(medico.getEspecialidade());
			}
			if (medico.getTelefone() != null && !medico.getTelefone().isEmpty()) {
				medicoAtual.setTelefone(medico.getTelefone());
			}

		}

		return medicoRepository.save(medicoAtual);
	}

	public List<Especialidade> listarEspecialidades() {
		//List<Especialidade> especialidades = new ArrayList<>();

		return Arrays.asList(Especialidade.values());
	}

	public List<Medico> listarMedicoPorEspecialidade(Especialidade especialidade) {
		return medicoRepository.findMedicoByEspecialidade(especialidade);
	}

	// Endpoint para facilitar os testes
	public List<Medico> cadastrarListaDeMedicos(List<Medico> listaMedicos) {
		return medicoRepository.saveAll(listaMedicos);
	}
}
