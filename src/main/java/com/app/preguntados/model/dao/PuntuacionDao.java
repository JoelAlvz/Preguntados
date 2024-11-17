package com.app.preguntados.model.dao;

import com.app.preguntados.model.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuntuacionDao extends JpaRepository<Puntuacion,Integer> {

}
