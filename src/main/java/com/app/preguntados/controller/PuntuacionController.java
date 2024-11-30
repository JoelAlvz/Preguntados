package com.app.preguntados.controller;

import com.app.preguntados.api.IPuntuacionService;
import com.app.preguntados.api.IUsuarioService;
import com.app.preguntados.model.dto.PuntuacionDTO;
import com.app.preguntados.model.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/puntuaciones")
public class PuntuacionController {
    @Autowired
    IPuntuacionService puntuacionService;
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping(value= "/getAll")
    public List<PuntuacionDTO> queryAllPuntuaciones(){
        return puntuacionService.queryAllPuntuaciones();
    }
    @PostMapping(value= "/insert")
    public int insertPuntuacion(@RequestBody PuntuacionDTO puntuacionDTO) {
        return puntuacionService.insertPuntuacion(puntuacionDTO);
    }
    @GetMapping("/usuario/{usuarioId}")
    public List<PuntuacionDTO> getPuntuacionesByUsuario(@PathVariable int usuarioId) {
        return puntuacionService.queryPuntuacionesByUsuario(usuarioId);
    }
    @PostMapping("/insertToUser/{usuarioId}")
    public int insertPuntuacionToUsuario(@PathVariable int usuarioId, @RequestBody PuntuacionDTO puntuacionDTO) {
        // Establece el usuario en el DTO
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuarioId); // necesita el ID para asociar
        puntuacionDTO.setUsuario(usuarioDTO);
        // Inserta la puntuación
        return puntuacionService.insertPuntuacion(puntuacionDTO);
    }
}
