package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import com.app.preguntados.controller.UsuarioController;
import com.app.preguntados.model.dto.UsuarioDTO;
import com.app.preguntados.service.UsuarioService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginController {
    @Autowired
    private UsuarioController usuarioController;
    @FXML
    private TextField usernameField;
    @Autowired
    private UsuarioActual usuarioActual;
    @FXML
    private PasswordField passwordField;

    public boolean validarLogin(String username, String password) {
        // Obtenemos la lista de usuarios
        List<UsuarioDTO> usuarios = usuarioController.queryAllUsuarios();

        // Recorremos la lista de usuarios y verificamos si alguno tiene el nombre y la contraseña correctos
        for (UsuarioDTO usuario : usuarios) {
            if (usuario.getNombre().equals(username) && usuario.getContraseña().equals(password)) {
                usuarioActual.setUsuario(usuario);
                return true;
            }
        }

        return false;
    }

    @FXML
    private void inicioLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Por favor, completa todos los campos.");
            return;
        }

        if (validarLogin(username, password)) {
            try {
                System.out.println("¡Inicio de sesión exitoso!");

                PreguntadosApplication.showMenuView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Credenciales incorrectas. Inténtalo de nuevo.");
        }
    }

    @FXML
    private void crearCuenta(){
        try {
            PreguntadosApplication.showRegisterView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

