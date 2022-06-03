package com.deps.consultorioapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deps.consultorioapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
