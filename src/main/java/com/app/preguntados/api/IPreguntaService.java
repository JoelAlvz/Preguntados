package com.app.preguntados.api;

import com.app.preguntados.model.Respuesta;
import com.app.preguntados.model.dto.PreguntaDTO;

import java.util.List;

public interface IPreguntaService {
    PreguntaDTO queryPregunta (PreguntaDTO preguntaDTO);
    List<PreguntaDTO> queryAllPreguntas();
    int insertPregunta(PreguntaDTO preguntaDTO);
    int updatePregunta(PreguntaDTO preguntaDTO);
    int deletePregunta(PreguntaDTO preguntaDTO);
}
