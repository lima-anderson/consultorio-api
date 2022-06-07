package com.deps.consultorioapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deps.consultorioapi.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findUsuarioByUsername(String username);

}
