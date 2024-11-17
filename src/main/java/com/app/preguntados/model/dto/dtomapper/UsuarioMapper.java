package com.app.preguntados.model.dto.dtomapper;

import com.app.preguntados.model.Usuario;
import com.app.preguntados.model.dto.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
    UsuarioDTO toDTO(Usuario usuario);
    List<UsuarioDTO> toDTOList(List<Usuario> usuarioList);
    Usuario toEntity(UsuarioDTO usuarioDTO);

}
