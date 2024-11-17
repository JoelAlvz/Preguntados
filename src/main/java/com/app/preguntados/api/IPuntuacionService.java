package com.app.preguntados.api;

import com.app.preguntados.model.dto.PuntuacionDTO;

import java.util.List;

public interface IPuntuacionService {
    PuntuacionDTO queryPuntuacion (PuntuacionDTO puntuacionDTO);
    List<PuntuacionDTO> queryAllPuntuaciones();
    int insertPuntuacion(PuntuacionDTO puntuacionDTO);
    int updatePuntuacion(PuntuacionDTO puntuacionDTO);
    int deletePuntuacion(PuntuacionDTO puntuacionDTO);
}
