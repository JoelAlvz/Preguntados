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
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PreguntaController preguntas;
    @Autowired
    private UsuarioActual usuario;
    @Autowired
    private PuntuacionController puntuacionController;

    PuntuacionDTO puntuacion;
    private int sumaPuntuacion=0;
    private int modoJuego= 1;
    private int contadorAciertos;
    private PreguntaDTO preguntaDTO;
    private int contadorResp;
    private int contadorComodin;
    private int vidas;

    Image coranzonSinVida = new Image("/com/app/preguntados/Imagenes/corazonSinVida.png");
    private List<Integer> listaId = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comodin.setVisible(false);
        contador.setVisible(false);
        res1.setDisable(true);
        res2.setDisable(true);
        res3.setDisable(true);
        res4.setDisable(true);
    }

    // Devuelve una pregunta aleatoria por su id
    public PreguntaDTO pregunta(){
        Random aleatorio = new Random();
        int id;
        do{id = aleatorio.nextInt(preguntas.queryAllPreguntas().size())+1;}
        while (listaId.contains(id));
        listaId.add(id);
        res1.setDisable(false);
        res2.setDisable(false);
        res3.setDisable(false);
        res4.setDisable(false);
        return preguntas.obtenerPreguntaPorId(id);
    }
    public void nuevaPregunta(){
        preguntaDTO = pregunta();
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
            nuevaPregunta();
        }
        if (contadorResp==10){

           finJuego();
        }
    }

    public void finJuego() throws Exception {

        if (modoJuego==1) {
            sumaPuntuacion = contadorAciertos;
        } else if (modoJuego==2) {
            sumaPuntuacion = contadorAciertos * 2;
        } else if (modoJuego==3) {
            sumaPuntuacion = contadorAciertos * 3;
        } else if (modoJuego==4) {
            sumaPuntuacion = contadorAciertos * 4;
        }
        puntuacion = new PuntuacionDTO();
        puntuacion.setPuntuacion(sumaPuntuacion);
        puntuacion.setPuntuacioncompe(0);
        puntuacionController.insertPuntuacionToUsuario(usuario.getUsuario().getId(), puntuacion);

        PreguntadosApplication.showFinJuegoView();
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
    }

    @FXML
    public void respuesta1(ActionEvent actionEvent) throws Exception {
        if (contadorResp<10) {
            if (preguntaDTO.getRespuestas().get(0).getVerdadera()) {
                contadorAciertos++;
            }else{vidasRestantes(); vidas--;}
            contadorResp++;
            if (contadorResp==10){

                finJuego();
            }
            nuevaPregunta();
        }
    }

    @FXML
    public void respuesta2(ActionEvent actionEvent) throws Exception {
        if (contadorResp<10) {
            if (preguntaDTO.getRespuestas().get(1).getVerdadera()) {
                contadorAciertos++;
            }else{vidasRestantes(); vidas--;}
            contadorResp++;
            if (contadorResp==10){

                finJuego();
            }
            nuevaPregunta();
        }
    }

    @FXML
    public void respuesta3(ActionEvent actionEvent) throws Exception {
        if (contadorResp<10) {
            if (preguntaDTO.getRespuestas().get(2).getVerdadera()) {
                contadorAciertos++;
            }else{vidasRestantes(); vidas--;}
            contadorResp++;
            if (contadorResp==10){

                finJuego();
            }
            nuevaPregunta();
        }
    }

    @FXML
    public void respuesta4(ActionEvent actionEvent) throws Exception {
        if (contadorResp<10) {
            if (preguntaDTO.getRespuestas().get(3).getVerdadera()) {
                contadorAciertos++;
            }else{vidasRestantes(); vidas--;}
            contadorResp++;
            if (contadorResp==10){

                finJuego();
            }
            nuevaPregunta();
        }
    }
}
