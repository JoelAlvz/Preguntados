package com.app.preguntados.model.dto;

import com.app.preguntados.model.Respuesta;

import java.util.List;

public class PreguntaDTO {
    private int id;
    private String pregunta;
    private String dificultad;
    private String categoria;
    private List<Respuesta> respuestas;

    public PreguntaDTO() {
    }

    public PreguntaDTO(String pregunta, String dificultad, String categoria) {

        this.pregunta = pregunta;
        this.dificultad = dificultad;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
