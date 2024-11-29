package com.app.preguntados.model.dto;

import com.app.preguntados.model.Pregunta;

public class RespuestaDTO {

    private int id;
    private String respuesta;
    private Boolean verdadera;
    private Pregunta pregunta;
    private int preguntaId;
    public RespuestaDTO() {
    }

    public RespuestaDTO(String respuesta, Boolean verdadera, int preguntaId) {

        this.respuesta = respuesta;
        this.verdadera = verdadera;
        this.pregunta = pregunta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Boolean getVerdadera() {
        return verdadera;
    }

    public void setVerdadera(Boolean verdadera) {
        this.verdadera = verdadera;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }
}
