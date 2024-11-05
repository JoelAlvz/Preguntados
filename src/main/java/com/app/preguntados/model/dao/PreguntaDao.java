package com.app.preguntados.model.dao;

import com.app.preguntados.model.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaDao extends JpaRepository<Pregunta, Integer> {
}
