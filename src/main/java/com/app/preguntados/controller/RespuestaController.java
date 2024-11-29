package com.app.preguntados.controller;


import com.app.preguntados.api.IRespuestaService;

import com.app.preguntados.model.dto.RespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/insertar")
    public String insertarRespuesta(@RequestBody RespuestaDTO nuevaRespuesta) {
        try {
            respuestaService.insertRespuesta(nuevaRespuesta);
            return "Respuesta insertada correctamente";
        } catch (Exception e) {
            return "Error al insertar la respuesta: " + e.getMessage();
        }
    }
    @PostMapping("/insertar/{preguntaId}")
    public String insertarRespuestaParaPregunta(@PathVariable int preguntaId, @RequestBody RespuestaDTO nuevaRespuesta) {
        try {
            respuestaService.insertRespuestaParaPregunta(preguntaId, nuevaRespuesta);
            return "Respuesta insertada correctamente para la pregunta con ID: " + preguntaId;
        } catch (Exception e) {
            return "Error al insertar la respuesta: " + e.getMessage();
        }
    }
}
