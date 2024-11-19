package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModoJuegoController {
    @Autowired
    private UsuarioActual usuario;

    @FXML
    public void lvfacil(ActionEvent actionEvent) throws Exception {
        if(usuario.usuarioActual()){
            PreguntadosApplication.showJuegoView();
        }else{
            System.out.println("error");
        }
    }
    @FXML
    public void lvavanzado(ActionEvent actionEvent) {
        if(usuario.puntuaciones()>100){

        }
    }
    @FXML
    public void lvmedio(ActionEvent actionEvent) {
    }
    @FXML
    public void lvmaestro(ActionEvent actionEvent) {
    }
    @FXML
    public void volver(ActionEvent actionEvent) {
    }
}
