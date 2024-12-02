package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import com.app.preguntados.controller.PreguntaController;
import com.app.preguntados.controller.RespuestaController;
import com.app.preguntados.model.dto.PreguntaDTO;
import com.app.preguntados.model.dto.RespuestaDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class NuevasPreguntasController implements Initializable {

    @Autowired
    private PreguntaController preguntaController;

    @Autowired
    private RespuestaController respuestaController;

    @FXML
    private TextField preguntaLabel, respuesta1, respuesta2,respuesta3,respuesta4;
    @FXML
    private CheckBox r1,r2,r3,r4;
    @FXML
    private VBox vBox;

    private static Image imagenFondo;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        r1.setFocusTraversable(false);
        r2.setFocusTraversable(false);
        r3.setFocusTraversable(false);
        r4.setFocusTraversable(false);
        vBox.setStyle(        "-fx-background-image: url('" + imagenFondo.getUrl() + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat;");
    }
    public static void initGraphics() {
        imagenFondo = new Image(NuevasPreguntasController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/fondoPreguntas.png").toExternalForm());
        System.out.println("Aplicación iniciada. Listo para interactuar con gráficos.");

    }
    public void añadirPregunta(ActionEvent actionEvent){
        try {
            // Validar las entradas (longitudes)
            if (preguntaLabel.getText().length() <= 60 &&
                    respuesta1.getText().length() <= 25 &&
                    respuesta2.getText().length() <= 25 &&
                    respuesta3.getText().length() <= 25 &&
                    respuesta4.getText().length() <= 25) {


                PreguntaDTO preguntaDTO = new PreguntaDTO(preguntaLabel.getText(),"creada","creada");

                if(r1.isSelected() || r2.isSelected() || r3.isSelected() || r4.isSelected()){

                    if (!preguntaLabel.getText().isEmpty() && !respuesta1.getText().isEmpty()  && !respuesta2.getText().isEmpty()&& !respuesta3.getText().isEmpty() && !respuesta4.getText().isEmpty())
                    {
                        // Llamar al controlador para insertar la pregunta y obtener su ID
                        int preguntaId = preguntaController.insertarPregunta(preguntaDTO);

                        // Insertar cada respuesta individualmente
                        RespuestaDTO respuestaDTO1 = new RespuestaDTO(respuesta1.getText(), (Boolean) r1.isSelected(), preguntaId);
                        respuestaController.insertarRespuestaParaPregunta(preguntaId, respuestaDTO1);

                        RespuestaDTO respuestaDTO2 = new RespuestaDTO(respuesta2.getText(), (Boolean) r2.isSelected(), preguntaId);
                        respuestaController.insertarRespuestaParaPregunta(preguntaId, respuestaDTO2);

                        RespuestaDTO respuestaDTO3 = new RespuestaDTO(respuesta3.getText(), (Boolean) r3.isSelected(), preguntaId);
                        respuestaController.insertarRespuestaParaPregunta(preguntaId, respuestaDTO3);

                        RespuestaDTO respuestaDTO4 = new RespuestaDTO(respuesta4.getText(), (Boolean) r4.isSelected(), preguntaId);
                        respuestaController.insertarRespuestaParaPregunta(preguntaId, respuestaDTO4);
                        actualizarCampos();
                    }
                }else {
                    System.out.println("Debe marcar una repsuesta correcta.");

                }

            } else {

                System.out.println("Error: Revise la longitud de los campos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al añadir pregunta y respuestas: " + e.getMessage());
        }
    }

    public void actualizarCampos(){
        preguntaLabel.setText("");
        respuesta1.setText("");
        respuesta2.setText("");
        respuesta3.setText("");
        respuesta4.setText("");

    }

    public void cancelar(ActionEvent actionEvent) throws Exception {
        PreguntadosApplication.showMenuView();
    }

    public void check1(MouseEvent actionEvent) {
        if (r1.isSelected()){
            r2.setDisable(true);
            r3.setDisable(true);
            r4.setDisable(true);
        } else {
            r2.setDisable(false);
            r3.setDisable(false);
            r4.setDisable(false);
        }
    }
    public void check2(MouseEvent actionEvent) {
        if (r2.isSelected()){
            r1.setDisable(true);
            r3.setDisable(true);
            r4.setDisable(true);
        } else {
            r1.setDisable(false);
            r3.setDisable(false);
            r4.setDisable(false);
        }
    }
    public void check3(MouseEvent actionEvent) {
        if (r3.isSelected()){
            r1.setDisable(true);
            r2.setDisable(true);
            r4.setDisable(true);
        } else {
            r1.setDisable(false);
            r2.setDisable(false);
            r4.setDisable(false);
        }

    }
    public void check4(MouseEvent actionEvent) {
        if (r4.isSelected()){
            r1.setDisable(true);
            r2.setDisable(true);
            r3.setDisable(true);
        } else {
            r1.setDisable(false);
            r2.setDisable(false);
            r3.setDisable(false);
        }
    }


}
