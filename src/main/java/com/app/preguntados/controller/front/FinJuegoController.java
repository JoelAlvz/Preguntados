package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import com.app.preguntados.controller.PuntuacionController;
import com.app.preguntados.controller.UsuarioController;
import com.app.preguntados.model.Usuario;
import com.app.preguntados.model.dao.UsuarioDao;
import com.app.preguntados.model.dto.PuntuacionDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class FinJuegoController implements Initializable {

    @FXML
    private Label puntuacionLabel;
    @FXML
    private Label aciertosLabel;
    @FXML
    private Label dificultadLabel;
    @FXML
    private Label puntuacionTotal,textoPuntuacion;
    @FXML
    private Button reiniciar, volver;

    @Autowired
    private UsuarioActual usuarioActual;
    @Autowired
    private PuntuacionController puntuacion;
    @Autowired
    private ModoJuegoController modoJuegoController;

    private int sumaPuntos;
    BackgroundImage backgroundImage;
    BackgroundImage backgroundImageHover;
    private static Image boton,botonHover;


    private static Image imagenFondo1, imagenFondo2;
    @FXML
    private VBox vBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calcularPuntuacion();
        puntuacionTotal.setText(String.valueOf(sumaPuntos));
        BackgroundSize size = new BackgroundSize(100, 100, true, true, true, false);
        backgroundImage = new BackgroundImage(boton, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        backgroundImageHover = new BackgroundImage(botonHover, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        reiniciar.setBackground(new Background(backgroundImage));
        volver.setBackground(new Background(backgroundImage));


    }
    public static void initGraphics() {
        imagenFondo1 = new Image(FinJuegoController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/finJuego1.png").toExternalForm());
        imagenFondo2 = new Image(FinJuegoController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/finJuego2.png").toExternalForm());
        System.out.println(imagenFondo1.getUrl());
        System.out.println("Aplicación iniciada. Listo para interactuar con gráficos.");
        boton = new Image(MenuController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/boton.png").toExternalForm());
        botonHover = new Image(MenuController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/botonHover.png").toExternalForm());

    }
    public int calcularAciertos(int puntos){
        if (usuarioActual.getModoJuego()==1){
            dificultadLabel.setText("Facil");
        } else if (usuarioActual.getModoJuego()==2) {
            dificultadLabel.setText("Media");
            puntos = puntos / 2;
        }else if (usuarioActual.getModoJuego()==3){
            dificultadLabel.setText("Avanzada");
            puntos = puntos / 3;
        } else if (usuarioActual.getModoJuego()==4) {
            dificultadLabel.setText("Maestro");
        }
        return puntos;
    }

    public void mostrarFondo(int puntos){
        if (puntos ==0){
            vBox.setStyle(        "-fx-background-image: url('" + imagenFondo1.getUrl() + "'); " +
                    "-fx-background-size: cover; " +
                    "-fx-background-repeat: no-repeat;");
        }else {
            vBox.setStyle(        "-fx-background-image: url('" + imagenFondo2.getUrl() + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat;");
        }

    }

    //Establece la suma de todas las puntuaciones y la puntuacion y aciertos de esta partida
    public void calcularPuntuacion(){
        int cont = 0;
        sumaPuntos=0;
        int maximaPuntuacion=0;
        if(usuarioActual.getModoJuego()<4) {
            for (PuntuacionDTO puntos : puntuacion.getPuntuacionesByUsuario(usuarioActual.getUsuario().getId())) {
                sumaPuntos += puntos.getPuntuacion();
                if ((puntuacion.getPuntuacionesByUsuario(usuarioActual.getUsuario().getId()).size() - 1) == cont) {
                    aciertosLabel.setText(String.valueOf(calcularAciertos(puntos.getPuntuacion())));
                    puntuacionLabel.setText(String.valueOf(puntos.getPuntuacion()));
                    mostrarFondo(puntos.getPuntuacion());
                }
                cont++;
            }
        }else{

            for (PuntuacionDTO puntos : puntuacion.getPuntuacionesByUsuario(usuarioActual.getUsuario().getId())) {
                sumaPuntos += puntos.getPuntuacioncompe();
                if(puntos.getPuntuacioncompe()>maximaPuntuacion){
                    maximaPuntuacion=puntos.getPuntuacioncompe();
                }
                if ((puntuacion.getPuntuacionesByUsuario(usuarioActual.getUsuario().getId()).size() - 1) == cont) {
                    aciertosLabel.setText(String.valueOf(calcularAciertos(puntos.getPuntuacioncompe())));
                    mostrarFondo(puntos.getPuntuacioncompe());
                }
                cont++;
            }
            textoPuntuacion.setText("PUNTUACIÓN MÁXIMA:");
            puntuacionLabel.setText(String.valueOf(maximaPuntuacion));
        }
    }
    @FXML
    public void reiniciar(ActionEvent actionEvent) throws Exception {
        sumaPuntos=0;
        for (PuntuacionDTO puntos : puntuacion.getPuntuacionesByUsuario(usuarioActual.getUsuario().getId())){
            sumaPuntos += puntos.getPuntuacion();
        }
        modoJuegoController.iniciarBotones();
        if (usuarioActual.getModoJuego()<4){
        PreguntadosApplication.showJuegoView();}else {PreguntadosApplication.showContadorView();}
    }

    @FXML
    public void volver(ActionEvent actionEvent) throws Exception {
        sumaPuntos=0;
        for (PuntuacionDTO puntos : puntuacion.getPuntuacionesByUsuario(usuarioActual.getUsuario().getId())){
            sumaPuntos += puntos.getPuntuacion();
        }
        modoJuegoController.iniciarBotones();
        PreguntadosApplication.showMenuView();
    }


    public void infoReiniciar(MouseEvent mouseEvent) {
        reiniciar.setBackground(new Background(backgroundImageHover));

    }

    public void infoVolver(MouseEvent mouseEvent) {
        volver.setBackground(new Background(backgroundImageHover));

    }

    public void volverEntidad(MouseEvent mouseEvent) {
        reiniciar.setBackground(new Background(backgroundImage));
        volver.setBackground(new Background(backgroundImage));

    }
}
