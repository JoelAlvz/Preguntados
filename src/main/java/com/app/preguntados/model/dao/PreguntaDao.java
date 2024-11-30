package com.app.preguntados.model.dao;

import com.app.preguntados.model.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreguntaDao extends JpaRepository<Pregunta, Integer> {
    Optional<Pregunta> findByPregunta(String nombre);
}
