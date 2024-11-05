package com.app.preguntados.model.dto.dtomapper;

import com.app.preguntados.model.Pregunta;
import com.app.preguntados.model.dto.PreguntaDTO;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface PreguntaMapper {
    PreguntaMapper INSTANCE = Mappers.getMapper(PreguntaMapper.class);
    PreguntaDTO toDTO(Pregunta pregunta);

    List<PreguntaDTO> toDTOList(List<Pregunta> productList);

    Pregunta toEntity(PreguntaDTO productDTO);
}
