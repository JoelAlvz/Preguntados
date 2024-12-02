package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import com.app.preguntados.controller.PreguntaController;
import com.app.preguntados.controller.PuntuacionController;
import com.app.preguntados.model.dto.PreguntaDTO;
import com.app.preguntados.model.dto.PuntuacionDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;


@Component
public class JuegoController implements Initializable {
    @FXML
    private Label preguntaLabel, contador;
    @FXML
    private Button res1, res2, res3, res4, empezar, comodin;
    @FXML
    private ImageView corazon1,corazon2, corazon3;
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


    PuntuacionDTO puntuacion;
    private int sumaPuntuacion=0;
    private int contadorAciertos;
    private PreguntaDTO preguntaDTO;
    private int contadorResp;
    private int contadorComodin;
    private int vidas;
    private int sumaPuntos=0;
    private static Image coranzonSinVida;
    private static Image coranzonConVida;
    private static Image imagenFondo1, imagenFondo2, imagenFondo3;
    private List<Integer> listaId = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comodin.setVisible(false);
        contador.setVisible(false);
        res1.setDisable(true);
        res2.setDisable(true);
        res3.setDisable(true);
        res4.setDisable(true);
        usuarioLabel.setText(usuario.getUsuario().getNombre());
        sumaPuntos=0;
        for (PuntuacionDTO puntos : puntuacionController.getPuntuacionesByUsuario(usuario.getUsuario().getId())){
            sumaPuntos = sumaPuntos + puntos.getPuntuacion();
        }
        puntuacionTotalLabel.setText(String.valueOf(sumaPuntos));

        vBox.setStyle(        "-fx-background-image: url('" + imagenFondo1.getUrl() + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat;");

    }


    public static void initGraphics() {
        coranzonSinVida = new Image(JuegoController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/corazonSinVida.png").toExternalForm());
        coranzonConVida = new Image(JuegoController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/corazonSano.png").toExternalForm());
        imagenFondo1 = new Image(MenuController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/fondoJuego1.png").toExternalForm());
        imagenFondo2 = new Image(MenuController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/fondoJuego2.png").toExternalForm());
        imagenFondo3 = new Image(MenuController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/fondoJuego3.png").toExternalForm());
        System.out.println(imagenFondo1.getUrl());
        System.out.println("Aplicación iniciada. Listo para interactuar con gráficos.");

    }

    // Devuelve una pregunta aleatoria por su id
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

    public void nuevaPregunta(){

        if(usuario.getModoJuego()==1){
            do {preguntaDTO=pregunta();}while (!preguntaDTO.getDificultad().equals("facil"));
        }else if (usuario.getModoJuego()==2) {
            do {preguntaDTO=pregunta();}while (!preguntaDTO.getDificultad().equals("medio"));
        } else if (usuario.getModoJuego()==3) {
            do {preguntaDTO=pregunta();}while (!preguntaDTO.getDificultad().equals("dificil"));
        }
        preguntaLabel.setText(preguntaDTO.getPregunta());
        res1.setText(preguntaDTO.getRespuestas().get(0).getRespuesta());
        res2.setText(preguntaDTO.getRespuestas().get(1).getRespuesta());
        res3.setText(preguntaDTO.getRespuestas().get(2).getRespuesta());
        res4.setText(preguntaDTO.getRespuestas().get(3).getRespuesta());
        contador.setText(contadorResp + "/10");
    }
    public void vidasRestantes() throws Exception {

        if(vidas==3){
            corazon1.setImage(coranzonSinVida);
        } else if (vidas ==2) {
            corazon2.setImage(coranzonSinVida);
        } else if (vidas==1) {
            corazon3.setImage(coranzonSinVida);
        } else if (vidas==0) {
            contadorAciertos=0;
            finJuego();
        }

    }

    public void comodin(ActionEvent actionEvent) throws Exception {
        if (contadorResp<10 && contadorComodin<3) {
            contadorAciertos++;
            contadorComodin++;
            contadorResp++;
            comodin.setText(String.valueOf(3-contadorComodin));
            setPuntuacionActualLabel();

            nuevaPregunta();
        }
        if (contadorResp==10){

           finJuego();
        }
      respuestaCorrecta();
    }

    public void finJuego() throws Exception {

        if (usuario.getModoJuego()==1) {
            sumaPuntuacion = contadorAciertos;
        } else if (usuario.getModoJuego()==2) {
            sumaPuntuacion = contadorAciertos * 2;
        } else if (usuario.getModoJuego()==3) {
            sumaPuntuacion = contadorAciertos * 3;
        }
        puntuacion = new PuntuacionDTO();
        puntuacion.setPuntuacion(sumaPuntuacion);
        puntuacion.setPuntuacioncompe(0);
        puntuacionController.insertPuntuacionToUsuario(usuario.getUsuario().getId(), puntuacion);

        PreguntadosApplication.showFinJuegoView();
    }

    public void setPuntuacionActualLabel(){
        if (usuario.getModoJuego()==2){
            puntuacionActualLabel.setText(String.valueOf(contadorAciertos*2));
        } else if (usuario.getModoJuego()==3) {
            puntuacionActualLabel.setText(String.valueOf(contadorAciertos*3));
        }else{
            puntuacionActualLabel.setText(String.valueOf(contadorAciertos));
        }

    }

    @FXML
    public void empezar(ActionEvent actionEvent) {
            contadorAciertos=0;
            vidas=3;
            nuevaPregunta();
            contadorResp=0;
            contadorComodin=0;
            listaId.clear();
            comodin.setText("3");
            contador.setText(contadorAciertos+"/10");
            empezar.setVisible(false);
            contador.setVisible(true);
            comodin.setVisible(true);
            preguntaLabel.setWrapText(true);
            corazon1.setImage(coranzonConVida);
            corazon2.setImage(coranzonConVida);
            corazon3.setImage(coranzonConVida);
    }

    @FXML
    public void respuesta1(ActionEvent actionEvent) throws Exception {
        if (contadorResp<10) {
            if (preguntaDTO.getRespuestas().get(0).getVerdadera()) {
                contadorAciertos++;
                respuestaCorrecta();
            }else{vidasRestantes(); vidas--; respuestaIncorrecta();}
            contadorResp++;
            if (contadorResp==10){

                finJuego();
            }
            setPuntuacionActualLabel();

            nuevaPregunta();
        }
    }

    @FXML
    public void respuesta2(ActionEvent actionEvent) throws Exception {
        if (contadorResp<10) {
            if (preguntaDTO.getRespuestas().get(1).getVerdadera()) {
                contadorAciertos++;
                respuestaCorrecta();
            }else{vidasRestantes(); vidas--;respuestaIncorrecta();}
            contadorResp++;
            if (contadorResp==10){

                finJuego();
            }
            setPuntuacionActualLabel();
            nuevaPregunta();
        }
    }

    @FXML
    public void respuesta3(ActionEvent actionEvent) throws Exception {
        if (contadorResp<10) {
            if (preguntaDTO.getRespuestas().get(2).getVerdadera()) {
                contadorAciertos++;
                respuestaCorrecta();
            }else{vidasRestantes(); vidas--;respuestaIncorrecta();}
            contadorResp++;
            if (contadorResp==10){

                finJuego();
            }
            setPuntuacionActualLabel();

            nuevaPregunta();
        }
    }

    @FXML
    public void respuesta4(ActionEvent actionEvent) throws Exception {
        if (contadorResp<10) {
            if (preguntaDTO.getRespuestas().get(3).getVerdadera()) {
                contadorAciertos++;
                respuestaCorrecta();
            }else{vidasRestantes(); vidas--;respuestaIncorrecta();}
            contadorResp++;
            if (contadorResp==10){

                finJuego();
            }
            setPuntuacionActualLabel();

            nuevaPregunta();
        }
    }
}
