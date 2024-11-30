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
    private Label puntuacionTotal,textoPuntuacion;


    @Autowired
    private UsuarioActual usuarioActual;
    @Autowired
    private PuntuacionController puntuacion;
    @Autowired
    private ModoJuegoController modoJuegoController;

    private int sumaPuntos;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calcularPuntuacion();

        puntuacionTotal.setText(String.valueOf(sumaPuntos));

    }

    public int calcularAciertos(int puntos){
        if (usuarioActual.getModoJuego()==1){
            dificultadLabel.setText("Facil");
        } else if (usuarioActual.getModoJuego()==2) {
            dificultadLabel.setText("Media");
            puntos = puntos / 2;
        }else if (usuarioActual.getModoJuego()==3){
            dificultadLabel.setText("Avanzada");
            puntos = puntos / 3;
        } else if (usuarioActual.getModoJuego()==4) {
            dificultadLabel.setText("Maestro");
        }
        return puntos;
    }

    //Establece la suma de todas las puntuaciones y la puntuacion y aciertos de esta partida
    public void calcularPuntuacion(){
        int cont = 0;
        sumaPuntos=0;
        int maximaPuntuacion=0;
        if(usuarioActual.getModoJuego()<4) {
            for (PuntuacionDTO puntos : puntuacion.getPuntuacionesByUsuario(usuarioActual.getUsuario().getId())) {
                sumaPuntos += puntos.getPuntuacion();
                if ((puntuacion.getPuntuacionesByUsuario(usuarioActual.getUsuario().getId()).size() - 1) == cont) {
                    aciertosLabel.setText(String.valueOf(calcularAciertos(puntos.getPuntuacion())));
                    puntuacionLabel.setText(String.valueOf(puntos.getPuntuacion()));
                }
                cont++;
            }
        }else{

            for (PuntuacionDTO puntos : puntuacion.getPuntuacionesByUsuario(usuarioActual.getUsuario().getId())) {
                sumaPuntos += puntos.getPuntuacioncompe();
                if(puntos.getPuntuacioncompe()>maximaPuntuacion){
                    maximaPuntuacion=puntos.getPuntuacioncompe();
                }
                if ((puntuacion.getPuntuacionesByUsuario(usuarioActual.getUsuario().getId()).size() - 1) == cont) {
                    aciertosLabel.setText(String.valueOf(calcularAciertos(puntos.getPuntuacioncompe())));

                }
                cont++;
            }
            textoPuntuacion.setText("PUNTUACIÓN MÁXIMA:");
            puntuacionLabel.setText(String.valueOf(maximaPuntuacion));
        }
    }
    @FXML
    public void reiniciar(ActionEvent actionEvent) throws Exception {
        sumaPuntos=0;
        for (PuntuacionDTO puntos : puntuacion.getPuntuacionesByUsuario(usuarioActual.getUsuario().getId())){
            sumaPuntos += puntos.getPuntuacion();
        }
        modoJuegoController.iniciarBotones();
        if (usuarioActual.getModoJuego()<4){
        PreguntadosApplication.showJuegoView();}else {PreguntadosApplication.showContadorView();}
    }

    @FXML
    public void volver(ActionEvent actionEvent) throws Exception {
        sumaPuntos=0;
        for (PuntuacionDTO puntos : puntuacion.getPuntuacionesByUsuario(usuarioActual.getUsuario().getId())){
            sumaPuntos += puntos.getPuntuacion();
        }
        modoJuegoController.iniciarBotones();
        PreguntadosApplication.showMenuView();
    }


}
