package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import com.app.preguntados.controller.PreguntaController;
import com.app.preguntados.controller.PuntuacionController;
import com.app.preguntados.model.dto.PreguntaDTO;
import com.app.preguntados.model.dto.PuntuacionDTO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

@Component
public class MContadorController implements Initializable {

    @FXML
    private Label contador; // Label para mostrar el contador
    @FXML
    private Label preguntaLabel;
    @FXML
    private Button res1, res2, res3, res4, empezar;
    @FXML
    private Label usuarioLabel,puntuacionActualLabel,puntuacionTotalLabel;
    @FXML
    private VBox vBox;
    @Autowired
    private PreguntaController preguntas;
    @Autowired
    private UsuarioActual usuario;
    @Autowired
    private PuntuacionController puntuacionController;
    private static Image imagenFondo1, imagenFondo2, imagenFondo3;

    PuntuacionDTO puntuacion;
    private PreguntaDTO preguntaDTO;
    private int tiempoRestante;
    private Timeline timeline;
    private List<Integer> listaId = new ArrayList<>();
    private int maximaPuntuacion;
    private int contadorAciertos;
    private int contadorResp;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        res1.setDisable(true);
        res2.setDisable(true);
        res3.setDisable(true);
        res4.setDisable(true);
        usuarioLabel.setText(usuario.getUsuario().getNombre());
        maximaPuntuacion();
        vBox.setStyle(        "-fx-background-image: url('" + imagenFondo1.getUrl() + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat;");
    }
    public static void initGraphics() {
        imagenFondo1 = new Image(MContadorController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/fondoJuego1.png").toExternalForm());
        imagenFondo2 = new Image(MContadorController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/fondoJuego2.png").toExternalForm());
        imagenFondo3 = new Image(MContadorController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/fondoJuego3.png").toExternalForm());
        System.out.println(imagenFondo1.getUrl());
        System.out.println("Aplicación iniciada. Listo para interactuar con gráficos.");

    }
    public void respuestaCorrecta(){
        vBox.setStyle(        "-fx-background-image: url('" + imagenFondo2.getUrl() + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat;");
    }
    public void respuestaIncorrecta(){
        vBox.setStyle(        "-fx-background-image: url('" + imagenFondo3.getUrl() + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat;");
    }
    private void maximaPuntuacion(){
        maximaPuntuacion=0;
        for (PuntuacionDTO puntos : puntuacionController.getPuntuacionesByUsuario(usuario.getUsuario().getId())){
            if(puntos.getPuntuacioncompe()>maximaPuntuacion){
                maximaPuntuacion=puntos.getPuntuacioncompe();
            }
        }
        puntuacionTotalLabel.setText(String.valueOf(maximaPuntuacion));
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
            try {
                finJuego();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        timeline.play();
    }

    private void actualizar() {
        if (tiempoRestante > 0) {
            contador.setText(String.valueOf(tiempoRestante));
            tiempoRestante--;
            System.out.println(contador.getText());
        }
        if (tiempoRestante <= 10) {
            contador.setStyle("-fx-text-fill: red;");
        }
    }

    public PreguntaDTO pregunta(){
        Random aleatorio = new Random();
        int id;
        do{id = aleatorio.nextInt(preguntas.queryAllPreguntas().size())+1;
        } while (listaId.contains(id));
        listaId.add(id);
        res1.setDisable(false);
        res2.setDisable(false);
        res3.setDisable(false);
        res4.setDisable(false);
        return preguntas.obtenerPreguntaPorId(id);
    }

    public void nuevaPregunta(){
        preguntaDTO=pregunta();
        preguntaLabel.setText(preguntaDTO.getPregunta());
        res1.setText(preguntaDTO.getRespuestas().get(0).getRespuesta());
        res2.setText(preguntaDTO.getRespuestas().get(1).getRespuesta());
        res3.setText(preguntaDTO.getRespuestas().get(2).getRespuesta());
        res4.setText(preguntaDTO.getRespuestas().get(3).getRespuesta());

    }
    public void empezar(ActionEvent actionEvent) {
        inicio(80);
        listaId.clear();
        nuevaPregunta();
        empezar.setVisible(false);
        contador.setVisible(true);
        maximaPuntuacion();
        contadorAciertos=0;
        contadorResp=0;
    }

    public void finJuego() throws Exception {
        int contadorPuntuacion=0;
        contadorPuntuacion = contadorResp - contadorAciertos;
        contadorAciertos = contadorAciertos-contadorPuntuacion;
        if (contadorAciertos<=0){
            contadorAciertos=0;
        }
        puntuacion = new PuntuacionDTO();
        puntuacion.setPuntuacion(0);
        puntuacion.setPuntuacioncompe(contadorAciertos);
        puntuacionController.insertPuntuacionToUsuario(usuario.getUsuario().getId(), puntuacion);
        System.out.println(puntuacion.getPuntuacioncompe());
        PreguntadosApplication.showFinJuegoView();
    }
    public void respuesta1(ActionEvent actionEvent) {
        if (preguntaDTO.getRespuestas().get(0).getVerdadera()) {
            contadorAciertos++;
            respuestaCorrecta();
        }else {respuestaIncorrecta();}
        contadorResp++;
        puntuacionActualLabel.setText(String.valueOf(contadorAciertos + "/" + contadorResp));
        nuevaPregunta();
    }

    public void respuesta2(ActionEvent actionEvent) {
        if (preguntaDTO.getRespuestas().get(1).getVerdadera()) {
        contadorAciertos++;
            respuestaCorrecta();
        }else {respuestaIncorrecta();}
        contadorResp++;
        puntuacionActualLabel.setText(String.valueOf(contadorAciertos + "/" + contadorResp));
        nuevaPregunta();

    }

    public void respuesta3(ActionEvent actionEvent) {
        if (preguntaDTO.getRespuestas().get(2).getVerdadera()) {
            contadorAciertos++;
            respuestaCorrecta();
        }else {respuestaIncorrecta();}
        contadorResp++;
        puntuacionActualLabel.setText(String.valueOf(contadorAciertos + "/" + contadorResp));
        nuevaPregunta();
    }

    public void respuesta4(ActionEvent actionEvent) {
        if (preguntaDTO.getRespuestas().get(3).getVerdadera()) {
            contadorAciertos++;
            respuestaCorrecta();
        }else {respuestaIncorrecta();}
        contadorResp++;
        puntuacionActualLabel.setText(String.valueOf(contadorAciertos + "/" + contadorResp));
        nuevaPregunta();
    }

}