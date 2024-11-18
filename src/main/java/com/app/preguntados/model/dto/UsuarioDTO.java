package com.app.preguntados.model.dto;

import com.app.preguntados.model.Puntuacion;
import jakarta.persistence.Column;

import java.util.List;

public class UsuarioDTO {
    private int id;
    private String nombre;
    private String contraseña;
    private String rango;
    private List<Puntuacion> puntuaciones;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nombre, String contraseña, String rango, List<Puntuacion> puntuaciones) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.rango = rango;
        this.puntuaciones = puntuaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public List<Puntuacion> getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(List<Puntuacion> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", rango='" + rango + '\'' +
                ", puntuaciones=" + puntuaciones +
                '}';
    }
}
