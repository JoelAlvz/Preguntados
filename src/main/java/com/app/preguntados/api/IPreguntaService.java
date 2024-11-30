package com.app.preguntados.api;

import com.app.preguntados.model.Respuesta;
import com.app.preguntados.model.dto.PreguntaDTO;
import com.app.preguntados.model.dto.UsuarioDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPreguntaService {
    PreguntaDTO queryPregunta (PreguntaDTO preguntaDTO);
    List<PreguntaDTO> queryAllPreguntas();
    int insertPregunta(PreguntaDTO preguntaDTO);
    int updatePregunta(PreguntaDTO preguntaDTO);
    int deletePregunta(PreguntaDTO preguntaDTO);
    @Transactional
    int deletePreguntaByNombre(PreguntaDTO preguntaDTO);
}
