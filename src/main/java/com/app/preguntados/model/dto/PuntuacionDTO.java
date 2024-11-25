package com.app.preguntados.model.dto;

import com.app.preguntados.model.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

public class PuntuacionDTO {

        private int id;
        private int puntuacion;
        private int puntuacioncompe;
        private UsuarioDTO usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getPuntuacioncompe() {
        return puntuacioncompe;
    }

    public void setPuntuacioncompe(int puntuacioncompe) {
        this.puntuacioncompe = puntuacioncompe;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
