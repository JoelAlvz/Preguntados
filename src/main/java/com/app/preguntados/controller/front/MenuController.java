package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MenuController implements Initializable {
    @Autowired
    private UsuarioActual usuario;

    @FXML
    private void startGame() throws Exception {
        System.out.println("Comenzar juego...");
        PreguntadosApplication.showModoJuegoView();
    }

    @FXML
    public void nuevasPreguntas(ActionEvent actionEvent) throws Exception {
        PreguntadosApplication.showNuevasPreguntasView();
    }

    @FXML
    public void borrarPregunta(ActionEvent actionEvent) throws Exception {
            PreguntadosApplication.showBorrarPreguntaView();
    }

    @FXML
    private void exitGame() throws Exception {
        PreguntadosApplication.showListaUsarioView();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
