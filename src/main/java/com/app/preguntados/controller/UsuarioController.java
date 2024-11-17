package com.app.preguntados.controller;

import com.app.preguntados.api.IRespuestaService;
import com.app.preguntados.api.IUsuarioService;
import com.app.preguntados.model.dto.RespuestaDTO;
import com.app.preguntados.model.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    IUsuarioService usuarioService;

    @GetMapping(value= "/getAll")
    public List<UsuarioDTO> queryAllUsuarios(){
        return usuarioService.queryAllUsuarios();
    }
}
