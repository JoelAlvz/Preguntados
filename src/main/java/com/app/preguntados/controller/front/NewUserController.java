package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import com.app.preguntados.controller.UsuarioController;
import com.app.preguntados.model.dto.UsuarioDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class NewUserController {

    @Autowired
    private UsuarioController usuarioController;
    @FXML
    private Label error;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordRepeatField;

    @FXML
    public void crearCuenta(ActionEvent actionEvent) throws Exception {
        boolean validador = false;
        //Comprueba que no haya campos vacios y que las contraseñas sean la misma
        if (!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            if(passwordField.getText().equals(passwordRepeatField.getText())) {
                validador= true;
            }else{error.setText("Escribe bien la contraseña");}
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
            UsuarioDTO usuario = new UsuarioDTO(usernameField.getText(), passwordField.getText(), "Hierro", null);
            usuarioController.insertUsuario(usuario);
            PreguntadosApplication.showMenuView();
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
