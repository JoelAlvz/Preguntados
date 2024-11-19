package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

@Component
public class MenuController {
    @FXML
    private void startGame() throws Exception {
        System.out.println("Comenzar juego...");
        PreguntadosApplication.showModoJuegoView();
    }
    @FXML
    public void ranking(ActionEvent actionEvent) {

    }
    @FXML
    public void perfil(ActionEvent actionEvent) {

    }

    @FXML
    private void exitGame() {
        System.exit(0);
    }
}
