package com.app.preguntados.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PREGUNTAS")
public class Pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PREGUNTA_ID")
    private int id;
    @Column (name = "PREGUNTA")
    private String pregunta;
    @Column (name = "DIFICULTAD")
    private String dificultad;
    @Column (name = "CATEGORIA")
    private String categoria;

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

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getCategorias() {
        return categoria;
    }

    public void setCategorias(String categorias) {
        this.categoria = categoria;
    }

}
