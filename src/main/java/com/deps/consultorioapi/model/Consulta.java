package com.deps.consultorioapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Consulta implements Serializable {

	private static final long serialVerionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConsulta;

	@OneToOne
	private Medico medico;
	@OneToOne
	private Paciente paciente;

	private LocalDate dataConsulta;

	public Consulta(){}

	public Consulta(Medico medico, Paciente paciente, LocalDate dataConsulta){
		this.medico = medico;
		this.paciente = paciente;
		this.dataConsulta = dataConsulta;
	}


}
