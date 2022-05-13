package com.deps.consultorioapi.repositories;

import com.deps.consultorioapi.model.Especialidade;
import com.deps.consultorioapi.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    List<Medico> findMedicoByEspecialidade(Especialidade especialidade);

}
