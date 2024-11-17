package com.app.preguntados.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @ManyToOne
    @JoinColumn(name = "PREGUNTA_ID", nullable = false) // "nullable = false" PREGUNTA_ID no puede ser nulo
    @JsonBackReference //parte de la relazion que no debe ser serializada
    private Pregunta pregunta;

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
