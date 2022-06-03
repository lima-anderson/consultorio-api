package com.deps.consultorioapi.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Consulta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConsulta;

	@OneToOne
	@JsonIgnore
	@NotNull
	private Medico medico;
	
	@OneToOne
	@JsonIgnore
	@NotNull
	private Paciente paciente;

	@NotNull
	private LocalDate dataConsulta;

	public Consulta(){}

	public Consulta(Medico medico, Paciente paciente, LocalDate dataConsulta){
		this.medico = medico;
		this.paciente = paciente;
		this.dataConsulta = dataConsulta;
	}

	public Long getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Long idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LocalDate getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(LocalDate dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Consulta consulta = (Consulta) o;
		return Objects.equals(idConsulta, consulta.idConsulta);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idConsulta);
	}

	public String getNomeMedico(){
		return medico.getNome();
	}

	public Especialidade getEspecialidade(){
		return medico.getEspecialidade();
	}

	public String getNomePaciente(){
		return paciente.getNome();
	}
	
	
}
