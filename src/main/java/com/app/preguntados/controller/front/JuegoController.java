package com.app.preguntados.controller.front;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;


@Component
public class JuegoController {
    @FXML
    private Label pregunta;
    @FXML
    private Button res1;
    @FXML
    private Button res2;
    @FXML
    private Button res3;
    @FXML
    private Button res4;



    @FXML
    public void respuesta(ActionEvent actionEvent) {

    }

    public void empezar(ActionEvent actionEvent) {


    }
}
