package com.app.preguntados.api;

import com.app.preguntados.model.dto.RespuestaDTO;
import com.app.preguntados.model.dto.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {
    UsuarioDTO queryUsuario (UsuarioDTO usuarioDTO);
    List<UsuarioDTO> queryAllUsuarios();
    int insertUsuario(UsuarioDTO usuarioDTO);
    int updateUsuario(UsuarioDTO usuarioDTO);
    int deleteUsuario(UsuarioDTO usuarioDTO);
}
