package com.app.preguntados.model.dto.dtomapper;
import com.app.preguntados.model.Respuesta;
import com.app.preguntados.model.dto.RespuestaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface RespuestaMapper {
    RespuestaMapper INSTANCE = Mappers.getMapper(RespuestaMapper.class);
    RespuestaDTO toDTO(Respuesta respuesta);

    List<RespuestaDTO> toDTOList(List<Respuesta> respuestaList);

    Respuesta toEntity(RespuestaDTO respuestaDTO);


}
