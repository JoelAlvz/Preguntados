package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import com.app.preguntados.controller.UsuarioController;
import com.app.preguntados.model.dto.UsuarioDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;


@Component
public class NewUserController implements Initializable {

    @Autowired
    private UsuarioController usuarioController;
    @FXML
    private Label error;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField usernameRepeatField;
    @FXML
    private VBox vBox;

    private static Image imagenFondo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vBox.setStyle( "-fx-background-image: url('" + imagenFondo.getUrl() + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat;");
    }
    public static void initGraphics() {
        imagenFondo = new Image(NewUserController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/fondoListaUser.png").toExternalForm());
        System.out.println(imagenFondo.getUrl());
        System.out.println("Aplicación iniciada. Listo para interactuar con gráficos.");
    }
    @FXML
    public void crearCuenta(ActionEvent actionEvent) throws Exception {
        boolean validador = false;
        //Comprueba que no haya campos vacios y que las contraseñas sean la misma
        if (!usernameField.getText().isEmpty()) {
            if(usernameField.getText().equals(usernameRepeatField.getText())) {
                validador= true;
            }else{error.setText("Los nombres deben coincidir");}
        }else{error.setText("Rellena todos los campos");}
        //Comprueba que no haya otro usuario con el mismo nombre
        for(UsuarioDTO usuario: usuarioController.queryAllUsuarios()){
            if (usernameField.getText().equals(usuario.getNombre())){
                validador=false;
                error.setText("Este usuario ya existe");
            }
        }
        if(validador){
            error.setText("");
            UsuarioDTO usuario = new UsuarioDTO(usernameField.getText(), "", "Hierro", null);
            usuarioController.insertUsuario(usuario);
            PreguntadosApplication.showMenuView();
        }
    }
    @FXML
    public void atras(ActionEvent actionEvent) {
        try {
            PreguntadosApplication.showListaUsarioView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
