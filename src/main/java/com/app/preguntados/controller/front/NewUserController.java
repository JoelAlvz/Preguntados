package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import com.app.preguntados.controller.UsuarioController;
import com.app.preguntados.model.dto.UsuarioDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewUserController {

    @Autowired
    private UsuarioController usuarioController;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordRepeatField;

    @FXML
    public void crearCuenta(ActionEvent actionEvent) {
        boolean validador = false;
        if (!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            if(passwordField.getText().equals(passwordRepeatField.getText())) {
                validador= true;
            }
        }
        if(validador){
            UsuarioDTO usuario = new UsuarioDTO(usernameField.getText(), passwordField.getText(), "Hierro", null);
            usuarioController.insertUsuario(usuario);
        }
    }
    @FXML
    public void atras(ActionEvent actionEvent) {
        try {
            PreguntadosApplication.showLoginView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
