package com.app.preguntados.model.dto;

import jakarta.persistence.Column;

public class RespuestaDTO {

    private int id;
    private String respuesta;
    private Boolean verdadera;

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
}
