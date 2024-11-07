package com.app.preguntados.controller;


import com.app.preguntados.api.IRespuestaService;

import com.app.preguntados.model.dto.RespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/respuestas")
public class RespuestaController {
    @Autowired
    IRespuestaService respuestaService;

    @GetMapping(value= "/getAll")
    public List<RespuestaDTO> queryAllRespuestas(){
        return respuestaService.queryAllRespuestas();
    }
}
