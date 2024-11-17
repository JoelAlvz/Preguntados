package com.app.preguntados.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="PUNTUACIONES")
public class Puntuacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PUNTUACION_ID")
    private int id;
    @Column(name = "PUNTUACION")
    private String puntuacion;
    @ManyToOne
    @JoinColumn(name = "USUARIO_ID", nullable = false) // "nullable = false" PREGUNTA_ID no puede ser nulo
    @JsonBackReference //parte de la relazion que no debe ser serializada
    private Usuario usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
