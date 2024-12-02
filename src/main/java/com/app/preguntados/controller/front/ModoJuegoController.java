package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import com.app.preguntados.controller.PuntuacionController;
import com.app.preguntados.model.dto.PuntuacionDTO;
import javafx.collections.ObservableList;
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
public class ModoJuegoController implements Initializable {
    @Autowired
    private UsuarioActual usuario;
    @Autowired
    private PuntuacionController puntuacion;
    @FXML
    private Button facil, medio, avanzado, maestro,botonVolver;
    private static Image boton,botonHover;
    private int sumaPuntos;
    @FXML
    private VBox vBox;
    private static Image imagenFondo;
    BackgroundImage backgroundImage;
    BackgroundImage backgroundImageHover;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sumaPuntos=0;
        medio.setDisable(true);
        avanzado.setDisable(true);
        for (PuntuacionDTO puntos : puntuacion.getPuntuacionesByUsuario(usuario.getUsuario().getId())){
            sumaPuntos += puntos.getPuntuacion();
        }
        System.out.println(sumaPuntos);
        iniciarBotones();
        vBox.setStyle( "-fx-background-image: url('" + imagenFondo.getUrl() + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat;");
        BackgroundSize size = new BackgroundSize(100, 100, true, true, true, false);
        backgroundImage = new BackgroundImage(boton, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        backgroundImageHover = new BackgroundImage(botonHover, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        facil.setBackground(new Background(backgroundImage));
        medio.setBackground(new Background(backgroundImage));
        avanzado.setBackground(new Background(backgroundImage));
        maestro.setBackground(new Background(backgroundImage));
        botonVolver.setBackground(new Background(backgroundImage));
    }

    public static void initGraphics() {
        imagenFondo = new Image(ModoJuegoController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/fondoModoJuego.png").toExternalForm());
        boton = new Image(MenuController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/boton.png").toExternalForm());
        botonHover = new Image(MenuController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/botonHover.png").toExternalForm());

        System.out.println(imagenFondo.getUrl());
        System.out.println("Aplicación iniciada. Listo para interactuar con gráficos.");
    }
    public void iniciarBotones(){
        if (sumaPuntos>50){
            medio.setDisable(false);
        }
        if (sumaPuntos>150){
            avanzado.setDisable(false);
        }
        System.out.println(puntuacion);
    }

    @FXML
    public void lvfacil(ActionEvent actionEvent) throws Exception {
        if(usuario.usuarioActual()){
            usuario.setModoJuego(1);

            PreguntadosApplication.showJuegoView();
        }else{
            System.out.println("error");
        }
    }
    @FXML
    public void lvmedio(ActionEvent actionEvent) throws Exception {
        if(usuario.usuarioActual()){
            usuario.setModoJuego(2);

            PreguntadosApplication.showJuegoView();
        }else{
            System.out.println("error");
        }
    }
    @FXML
    public void lvavanzado(ActionEvent actionEvent) throws Exception {
        if(usuario.usuarioActual()){
            usuario.setModoJuego(3);

            PreguntadosApplication.showJuegoView();
        }else{
            System.out.println("error");
        }
    }
    @FXML
    public void lvmaestro(ActionEvent actionEvent) throws Exception {
        if(usuario.usuarioActual()){
            usuario.setModoJuego(4);
            PreguntadosApplication.showContadorView();

        }else{
            System.out.println("error");
        }

    }
    @FXML
    public void volver(ActionEvent actionEvent) throws Exception {
        PreguntadosApplication.showMenuView();
    }

    public void infoFacil(MouseEvent mouseEvent) {
        facil.setBackground(new Background(backgroundImageHover));
    }
    public void infoMedio(MouseEvent mouseEvent) {
        medio.setBackground(new Background(backgroundImageHover));
    }
    public void infoAvanzado(MouseEvent mouseEvent) {
        avanzado.setBackground(new Background(backgroundImageHover));
    }

    public void infoMaestro(MouseEvent mouseEvent) {
        maestro.setBackground(new Background(backgroundImageHover));
    }


    public void volverEstado(MouseEvent mouseEvent) {
        facil.setBackground(new Background(backgroundImage));
        medio.setBackground(new Background(backgroundImage));
        avanzado.setBackground(new Background(backgroundImage));
        maestro.setBackground(new Background(backgroundImage));
        botonVolver.setBackground(new Background(backgroundImage));

    }

    public void infoHover(MouseEvent mouseEvent) {
        botonVolver.setBackground(new Background(backgroundImageHover));

    }
}
