package com.app.preguntados.controller;

import com.app.preguntados.api.IPreguntaService;
import com.app.preguntados.model.dto.PreguntaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
