package com.deps.consultorioapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deps.consultorioapi.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
