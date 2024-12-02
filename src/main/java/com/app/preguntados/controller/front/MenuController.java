package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


import javafx.scene.layout.*;

import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MenuController implements Initializable {
    @Autowired
    private UsuarioActual usuario;
    private static Image imagenFondo,boton,botonHover;

    @FXML
    private VBox vBox;
    @FXML
    private Text comentario;
    @FXML
    private Button jugar,salir,lista,nueva;
    BackgroundImage backgroundImage;
    BackgroundImage backgroundImageHover;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vBox.setStyle( "-fx-background-image: url('" + imagenFondo.getUrl() + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat;");
        BackgroundSize size = new BackgroundSize(100, 100, true, true, true, false);
        backgroundImage = new BackgroundImage(boton, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        backgroundImageHover = new BackgroundImage(botonHover, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        comentario.setText("¡Hola "+usuario.getUsuario().getNombre()+"! ¿Que te apetece hacer?");
        jugar.setBackground(new Background(backgroundImage));
        salir.setBackground(new Background(backgroundImage));
        lista.setBackground(new Background(backgroundImage));
        nueva.setBackground(new Background(backgroundImage));


    }

    public static void initGraphics() {
        imagenFondo = new Image(MenuController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/fondoMenu.png").toExternalForm());
        boton = new Image(MenuController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/boton.png").toExternalForm());
        botonHover = new Image(MenuController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/botonHover.png").toExternalForm());

        System.out.println(imagenFondo.getUrl());
        System.out.println("Aplicación iniciada. Listo para interactuar con gráficos.");
    }

    @FXML
    private void startGame() throws Exception {
        System.out.println("Comenzar juego...");
        PreguntadosApplication.showModoJuegoView();
    }

    @FXML
    public void nuevasPreguntas(ActionEvent actionEvent) throws Exception {
        PreguntadosApplication.showNuevasPreguntasView();
    }

    @FXML
    public void borrarPregunta(ActionEvent actionEvent) throws Exception {
            PreguntadosApplication.showBorrarPreguntaView();
    }

    @FXML
    private void exitGame() throws Exception {
        PreguntadosApplication.showListaUsarioView();
    }

    public void infoJugar(MouseEvent mouseEvent) {
        comentario.setText("¡Juega, diviertete y aprende!");
        jugar.setBackground(new Background(backgroundImageHover));

    }

    public void infoCerrar(MouseEvent mouseEvent) {
        comentario.setText("Elige otro usuario...");
        salir.setBackground(new Background(backgroundImageHover));
    }

    public void volverComentario(MouseEvent mouseEvent) {
        comentario.setText("¡Hola "+usuario.getUsuario().getNombre()+"! ¿Que te apetece hacer?");
        jugar.setBackground(new Background(backgroundImage));
        nueva.setBackground(new Background(backgroundImage));
        salir.setBackground(new Background(backgroundImage));
        lista.setBackground(new Background(backgroundImage));
    }

    public void infoPreguntas(MouseEvent mouseEvent) {
        comentario.setText("¡Aquí puedes crear nuevas preguntas!");
        nueva.setBackground(new Background(backgroundImageHover));
    }

    public void infoLista(MouseEvent mouseEvent) {
        comentario.setText("¡Visualiza y elimina tus preguntas!");
        lista.setBackground(new Background(backgroundImageHover));
    }
}
