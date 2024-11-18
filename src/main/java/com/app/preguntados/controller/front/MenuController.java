package com.app.preguntados.controller.front;

import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

@Component
public class MenuController {
    @FXML
    private void startGame() {
        System.out.println("Comenzar juego...");

    }

    @FXML
    private void exitGame() {
        System.exit(0);
    }
}
