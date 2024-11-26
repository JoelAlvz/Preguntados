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
import javafx.scene.control.Label;
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
    private Label puntuacionTotal;

    @Autowired
    private UsuarioActual usuarioActual;
    @Autowired
    private PuntuacionController puntuacion;

    private int sumaPuntos;

    private int modoJuego=1;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        calcularPuntuacion();


        puntuacionTotal.setText(String.valueOf(sumaPuntos));
        if (modoJuego==1){
            dificultadLabel.setText("Facil");
        } else if (modoJuego==2) {
            dificultadLabel.setText("Media");
        }else if (modoJuego==3){
            dificultadLabel.setText("Avanzada");
        } else if (modoJuego==4) {
            dificultadLabel.setText("Maestro");
        }
    }
    //Establece la suma de todas las puntuaciones y la puntuacion y aciertos de esta partida
    public void calcularPuntuacion(){
        int cont = 0;
        sumaPuntos=0;
        for (PuntuacionDTO puntos : puntuacion.getPuntuacionesByUsuario(usuarioActual.getUsuario().getId())){
            sumaPuntos = sumaPuntos + puntos.getPuntuacion();
            if ( (puntuacion.getPuntuacionesByUsuario(usuarioActual.getUsuario().getId()).size()-1)==cont){
                aciertosLabel.setText(String.valueOf(puntos.getPuntuacion()));


                puntuacionLabel.setText(String.valueOf(puntos.getPuntuacion()));
            }
            cont++;
        }
    }
    @FXML
    public void reiniciar(ActionEvent actionEvent) throws Exception {
        PreguntadosApplication.showJuegoView();
    }

    @FXML
    public void volver(ActionEvent actionEvent) throws Exception {
        PreguntadosApplication.showMenuView();
    }


}
