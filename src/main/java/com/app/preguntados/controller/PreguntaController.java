package com.app.preguntados.controller;

import com.app.preguntados.api.IPreguntaService;
import com.app.preguntados.model.dto.PreguntaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/categoria/{categoria}")
    public List<PreguntaDTO> obtenerPreguntasPorCategoria(@PathVariable String categoria) {
        PreguntaDTO preguntaDTO = new PreguntaDTO();
        List<PreguntaDTO> listaPreguntas = new ArrayList<>();
       for (PreguntaDTO pregunta : preguntaService.queryAllPreguntas()){
           if(pregunta.getCategoria().equals(categoria)){
               listaPreguntas.add(pregunta);
           }
       }
       return listaPreguntas;
    }
    @GetMapping("/dificultad/{dificultad}")
    public List<PreguntaDTO> obtenerPreguntasPorDificultad(@PathVariable String dificultad) {
        PreguntaDTO preguntaDTO = new PreguntaDTO();
        List<PreguntaDTO> listaPreguntas = new ArrayList<>();
        for (PreguntaDTO pregunta : preguntaService.queryAllPreguntas()){
            if(pregunta.getDificultad().equals(dificultad)){
                listaPreguntas.add(pregunta);
            }
        }
        return listaPreguntas;
    }
    @PostMapping("/insertar")
    public int insertarPregunta(@RequestBody PreguntaDTO nuevaPregunta) {

          return preguntaService.insertPregunta(nuevaPregunta);

    }
}
