package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import com.app.preguntados.controller.UsuarioController;
import com.app.preguntados.model.dto.UsuarioDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ListaUsuarioController implements Initializable {
    @Autowired
    private UsuarioController usuarioController;

    @Autowired
    private UsuarioActual usuarioActual;

    @FXML
    private ListView<String> lista;
    @FXML
    private VBox vBox;
    @FXML
    private Button nuevo,eliminar,entrar;
    private ObservableList<String> usuarios;
    private static Image imagenFondo;
    BackgroundImage backgroundImage;
    BackgroundImage backgroundImageHover;
    private static Image boton,botonHover;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarios = FXCollections.observableArrayList();
        lista.setItems(usuarios);
        cargarUsuarios();
        vBox.setStyle( "-fx-background-image: url('" + imagenFondo.getUrl() + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat;");
        BackgroundSize size = new BackgroundSize(100, 100, true, true, true, false);
        backgroundImage = new BackgroundImage(boton, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        backgroundImageHover = new BackgroundImage(botonHover, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        eliminar.setBackground(new Background(backgroundImage));
        nuevo.setBackground(new Background(backgroundImage));
        entrar.setBackground(new Background(backgroundImage));
    }

    public static void initGraphics() {
        imagenFondo = new Image(ListaUsuarioController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/fondoListaUser.png").toExternalForm());
        System.out.println(imagenFondo.getUrl());
        boton = new Image(MenuController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/boton.png").toExternalForm());
        botonHover = new Image(MenuController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/botonHover.png").toExternalForm());

        System.out.println("Aplicación iniciada. Listo para interactuar con gráficos.");
    }
    private void cargarUsuarios() {
        try {
            List<UsuarioDTO> usuariosList = usuarioController.queryAllUsuarios();
            for (UsuarioDTO usuario : usuariosList) {
                usuarios.add(usuario.getNombre());
            }
        } catch (Exception e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }
    }
    @FXML
    public void eliminarUsuario() {
        String seleccionado = lista.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                UsuarioDTO usuarioDTO = new UsuarioDTO();
                usuarioDTO.setNombre(seleccionado);
                usuarioController.deleteUsuarioByNombre(seleccionado);

                // Eliminar de la lista en la interfaz
                usuarios.remove(seleccionado);
                lista.setItems(usuarios);
            } catch (Exception e) {
                System.err.println("Error al eliminar usuario: " + e.getMessage());
            }
        }
    }

    public void escogerUsuario(ActionEvent actionEvent) throws Exception {
        String seleccionado = lista.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            List<UsuarioDTO> usuarios = usuarioController.queryAllUsuarios();
            for (UsuarioDTO usuario : usuarios) {
                if (usuario.getNombre().equals(seleccionado)) {
                    usuarioActual.setUsuario(usuario);
                }
            }
            PreguntadosApplication.showMenuView();
        }
    }

    public void nuevoUsuario(ActionEvent actionEvent) throws Exception {
        PreguntadosApplication.showRegisterView();
    }


    public void infoEntrar(MouseEvent mouseEvent) {
        entrar.setBackground(new Background(backgroundImageHover));
    }

    public void infoNuevo(MouseEvent mouseEvent) {
        nuevo.setBackground(new Background(backgroundImageHover));
    }

    public void infoEliminar(MouseEvent mouseEvent) {
        eliminar.setBackground(new Background(backgroundImageHover));
    }

    public void volverEntidad(MouseEvent mouseEvent) {
        eliminar.setBackground(new Background(backgroundImage));
        nuevo.setBackground(new Background(backgroundImage));
        entrar.setBackground(new Background(backgroundImage));
    }
}
