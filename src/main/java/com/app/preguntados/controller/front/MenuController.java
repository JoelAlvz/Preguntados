package com.app.preguntados.controller.front;

import javafx.fxml.FXML;

public class MenuController {
    @FXML
    private void startGame() {
        System.out.println("Comenzar juego...");
        // Aquí podrías cargar otra ventana para el juego.
    }

    @FXML
    private void exitGame() {
        System.exit(0);
    }
}
