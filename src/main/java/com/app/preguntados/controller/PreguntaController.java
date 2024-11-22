package com.app.preguntados.controller;

import com.app.preguntados.api.IPreguntaService;
import com.app.preguntados.model.Respuesta;
import com.app.preguntados.model.dto.PreguntaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/preguntas")
public class PreguntaController {
    @Autowired
    IPreguntaService preguntaService;

    @GetMapping(value= "/getAll")
    public List<PreguntaDTO> queryAllPreguntas(){
        return preguntaService.queryAllPreguntas();
    }

    @GetMapping("/{id}")
    public PreguntaDTO obtenerPreguntaPorId(@PathVariable int id) {
        PreguntaDTO preguntaDTO = new PreguntaDTO();
        preguntaDTO.setId(id);
        return preguntaService.queryPregunta(preguntaDTO);
    }
}
