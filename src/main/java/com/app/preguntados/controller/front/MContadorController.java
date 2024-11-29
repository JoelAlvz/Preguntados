package com.app.preguntados.controller.front;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.springframework.stereotype.Component;

@Component
public class MContadorController {

    @FXML
    private Label contador; // Label para mostrar el contador

    private int tiempoRestante; // Tiempo restante en segundos
    private Timeline timeline;

    public void initialize() {
        inicio(10); // Inicia el contador desde 10 segundos
    }

    private void inicio(int seconds) {
        tiempoRestante = seconds;

        // Crear el Timeline que se ejecuta cada segundo
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            actualizar(); // Actualizar la etiqueta
        }));

        timeline.setCycleCount(seconds);
        timeline.setOnFinished(event -> {
            contador.setText("¡Tiempo finalizado!");
        });

        timeline.play();
    }

    private void actualizar() {
        if (tiempoRestante > 0) {
            contador.setText(String.valueOf(tiempoRestante));
            tiempoRestante--;
            System.out.println(contador.getText());
        }
        if (tiempoRestante <= 3) {
            contador.setStyle("-fx-text-fill: red;");
        }
    }
}