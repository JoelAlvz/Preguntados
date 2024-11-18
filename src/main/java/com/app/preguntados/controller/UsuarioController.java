package com.app.preguntados.controller;

import com.app.preguntados.api.IRespuestaService;
import com.app.preguntados.api.IUsuarioService;
import com.app.preguntados.model.dto.RespuestaDTO;
import com.app.preguntados.model.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    IUsuarioService usuarioService;

    @GetMapping(value = "/getAll")
    public List<UsuarioDTO> queryAllUsuarios() {
        return usuarioService.queryAllUsuarios();
    }

    @PostMapping(value= "/insert")
    public int insertUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        // Llamamos al servicio para insertar el usuario y devolver su id
        return usuarioService.insertUsuario(usuarioDTO);
    }

}
