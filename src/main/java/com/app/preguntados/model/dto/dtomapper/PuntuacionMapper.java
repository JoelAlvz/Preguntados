package com.app.preguntados.model.dto.dtomapper;

import com.app.preguntados.model.Puntuacion;
import com.app.preguntados.model.dto.PuntuacionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PuntuacionMapper {
    PuntuacionMapper INSTANCE = Mappers.getMapper(PuntuacionMapper.class);
    PuntuacionDTO toDTO(Puntuacion puntuacion);
    List<PuntuacionDTO> toDTOList(List<Puntuacion> puntuacionList);
    Puntuacion toEntity(PuntuacionDTO puntuacionDTO);
}
