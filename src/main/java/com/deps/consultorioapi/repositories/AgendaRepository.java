package com.deps.consultorioapi.repositories;

import com.deps.consultorioapi.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
