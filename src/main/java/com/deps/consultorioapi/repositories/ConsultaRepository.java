package com.deps.consultorioapi.repositories;

import com.deps.consultorioapi.model.Consulta;
import com.deps.consultorioapi.model.Medico;
import com.deps.consultorioapi.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findConsultaByMedico(Medico medico);

    List<Consulta> findConsultaByPaciente(Paciente paciente);

    List<Consulta> findConsultaByDataConsultaAndMedico(LocalDate data, Medico medico);
}
