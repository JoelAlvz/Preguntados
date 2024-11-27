package com.app.preguntados.controller.front;

import com.app.preguntados.model.Puntuacion;

import com.app.preguntados.model.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioActual {
    private UsuarioDTO usuario;
    private int modoJuego;

    public int getModoJuego() {
        return modoJuego;
    }

    public void setModoJuego(int modoJuego) {
        this.modoJuego = modoJuego;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    public int puntuaciones(){
        int suma=0;
            for(Puntuacion puntuacion : usuario.getPuntuaciones()){
                suma+=puntuacion.getPuntuacion();
            }
            return suma;
    }
    public boolean usuarioActual() {
        return usuario != null;
    }

    public void cerrarSesion() {
        this.usuario = null;
    }
}
