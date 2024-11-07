package com.app.preguntados.api;

import com.app.preguntados.model.dto.RespuestaDTO;

import java.util.List;

public interface IRespuestaService {
    RespuestaDTO queryRespuesta (RespuestaDTO respuestaDTO);
    List<RespuestaDTO> queryAllRespuestas();
    int insertRespuesta(RespuestaDTO respuestaDTO);
    int updateRespuesta(RespuestaDTO respuestaDTO);
    int deleteRespuesta(RespuestaDTO respuestaDTO);
}
