package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validación básica de ejemplo
        if (username.equals("admin") && password.equals("1234")) {
            try {
                PreguntadosApplication.showMenuView(); // Cambiar a la vista del menú
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Credenciales incorrectas");
        }
    }
}
