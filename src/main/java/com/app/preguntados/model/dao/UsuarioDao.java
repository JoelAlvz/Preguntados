package com.app.preguntados.model.dao;

import com.app.preguntados.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario,Integer> {
}
