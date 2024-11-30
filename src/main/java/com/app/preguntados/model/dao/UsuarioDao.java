package com.app.preguntados.model.dao;

import com.app.preguntados.model.Puntuacion;
import com.app.preguntados.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface UsuarioDao extends JpaRepository<Usuario,Integer> {
    Optional<Usuario> findByNombre(String nombre);
}
