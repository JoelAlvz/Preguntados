package com.app.preguntados.controller;

import com.app.preguntados.api.IPuntuacionService;
import com.app.preguntados.api.IUsuarioService;
import com.app.preguntados.model.dto.PuntuacionDTO;
import com.app.preguntados.model.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/puntuaciones")
public class PuntuacionController {
    @Autowired
    IPuntuacionService puntuacionService;

    @GetMapping(value= "/getAll")
    public List<PuntuacionDTO> queryAllPuntuaciones(){
        return puntuacionService.queryAllPuntuaciones();
    }
}
