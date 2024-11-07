package com.app.preguntados.model;

import jakarta.persistence.*;

@Entity
@Table(name="RESPUESTAS")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESPUESTA_ID")
    private int id;
    @Column(name = "RESPUESTA")
    private String respuesta;
    @Column (name = "VERDADERA")
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
