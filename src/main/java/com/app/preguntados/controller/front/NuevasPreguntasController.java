package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import com.app.preguntados.controller.PreguntaController;
import com.app.preguntados.controller.RespuestaController;
import com.app.preguntados.model.dto.PreguntaDTO;
import com.app.preguntados.model.dto.RespuestaDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NuevasPreguntasController {

    @Autowired
    private PreguntaController preguntaController;

    @Autowired
    private RespuestaController respuestaController;

    @FXML
    private TextField preguntaLabel, respuesta1, respuesta2,respuesta3,respuesta4;
    @FXML
    private CheckBox r1,r2,r3,r4;

    public void añadirPregunta(ActionEvent actionEvent) {
        try {
            // Validar las entradas (longitudes)
            if (preguntaLabel.getText().length() <= 60 &&
                    respuesta1.getText().length() <= 25 &&
                    respuesta2.getText().length() <= 25 &&
                    respuesta3.getText().length() <= 25 &&
                    respuesta4.getText().length() <= 25) {


                PreguntaDTO preguntaDTO = new PreguntaDTO(preguntaLabel.getText(),"creada","creada");


                // Llamar al controlador para insertar la pregunta y obtener su ID
                int preguntaId = preguntaController.insertarPregunta(preguntaDTO);

                // Insertar cada respuesta individualmente
                RespuestaDTO respuestaDTO1 = new RespuestaDTO(respuesta1.getText(), (Boolean) r1.isSelected(), preguntaId);
                respuestaController.insertarRespuestaParaPregunta(preguntaId, respuestaDTO1);

                RespuestaDTO respuestaDTO2 = new RespuestaDTO(respuesta2.getText(),(Boolean) r2.isSelected() , preguntaId);
                respuestaController.insertarRespuestaParaPregunta(preguntaId, respuestaDTO2);

                RespuestaDTO respuestaDTO3 = new RespuestaDTO(respuesta3.getText(),(Boolean) r3.isSelected() , preguntaId);
                respuestaController.insertarRespuestaParaPregunta(preguntaId, respuestaDTO3);

                RespuestaDTO respuestaDTO4 = new RespuestaDTO(respuesta4.getText(),(Boolean) r4.isSelected() , preguntaId);
                respuestaController.insertarRespuestaParaPregunta(preguntaId, respuestaDTO4);


            } else {

                System.out.println("Error: Revise la longitud de los campos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al añadir pregunta y respuestas: " + e.getMessage());
        }
    }

    public void cancelar(ActionEvent actionEvent) throws Exception {
        PreguntadosApplication.showMenuView();
    }

}
