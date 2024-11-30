package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import com.app.preguntados.controller.PuntuacionController;
import com.app.preguntados.model.dto.PuntuacionDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ModoJuegoController implements Initializable {
    @Autowired
    private UsuarioActual usuario;
    @Autowired
    private PuntuacionController puntuacion;
    @FXML
    private Button facil, medio, avanzado;

    private int sumaPuntos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sumaPuntos=0;
        medio.setDisable(true);
        avanzado.setDisable(true);
        for (PuntuacionDTO puntos : puntuacion.getPuntuacionesByUsuario(usuario.getUsuario().getId())){
            sumaPuntos += puntos.getPuntuacion();
        }
        System.out.println(sumaPuntos);
        iniciarBotones();
    }

    public void iniciarBotones(){
        if (sumaPuntos>50){
            medio.setDisable(false);
        }
        if (sumaPuntos>150){
            avanzado.setDisable(false);
        }
        System.out.println(puntuacion);
    }

    @FXML
    public void lvfacil(ActionEvent actionEvent) throws Exception {
        if(usuario.usuarioActual()){
            usuario.setModoJuego(1);

            PreguntadosApplication.showJuegoView();
        }else{
            System.out.println("error");
        }
    }
    @FXML
    public void lvmedio(ActionEvent actionEvent) throws Exception {
        if(usuario.usuarioActual()){
            usuario.setModoJuego(2);

            PreguntadosApplication.showJuegoView();
        }else{
            System.out.println("error");
        }
    }
    @FXML
    public void lvavanzado(ActionEvent actionEvent) throws Exception {
        if(usuario.usuarioActual()){
            usuario.setModoJuego(3);

            PreguntadosApplication.showJuegoView();
        }else{
            System.out.println("error");
        }
    }
    @FXML
    public void lvmaestro(ActionEvent actionEvent) throws Exception {
        if(usuario.usuarioActual()){
            usuario.setModoJuego(4);
            PreguntadosApplication.showContadorView();

        }else{
            System.out.println("error");
        }

    }
    @FXML
    public void volver(ActionEvent actionEvent) throws Exception {
        PreguntadosApplication.showMenuView();
    }


}
