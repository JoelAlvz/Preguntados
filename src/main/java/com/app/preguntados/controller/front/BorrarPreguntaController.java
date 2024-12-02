package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import com.app.preguntados.controller.PreguntaController;
import com.app.preguntados.model.dto.PreguntaDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class BorrarPreguntaController implements Initializable {

    @Autowired
    private PreguntaController preguntaController;
    @FXML
    private ListView<String> listaPreguntas;
    @FXML
    private VBox vBox;

    private static Image imagenFondo;
    private ObservableList<String> preguntas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        preguntas = FXCollections.observableArrayList();
        listaPreguntas.setItems(preguntas);
        cargarPreguntas();
        vBox.setStyle(        "-fx-background-image: url('" + imagenFondo.getUrl() + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat;");
    }
    public static void initGraphics() {
        imagenFondo = new Image(BorrarPreguntaController.class.getClassLoader().getResource("com/app/preguntados/Imagenes/fondoPreguntas.png").toExternalForm());
        System.out.println("Aplicación iniciada. Listo para interactuar con gráficos.");

    }
    public void eliminarPregunta(ActionEvent actionEvent) {
        String seleccionado = listaPreguntas.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                PreguntaDTO preguntaDTO = new PreguntaDTO();
                preguntaDTO.setPregunta(seleccionado);
                preguntaController.deletePreguntaByNombre(seleccionado);

                // Eliminar de la lista en la interfaz
                preguntas.remove(seleccionado);
                listaPreguntas.setItems(preguntas);
            } catch (Exception e) {
                System.err.println("Error al eliminar usuario: " + e.getMessage());
            }
        }
    }

    public void volver(ActionEvent actionEvent) throws Exception {
        PreguntadosApplication.showMenuView();
    }



    private void cargarPreguntas() {
        try {
            List<PreguntaDTO> preguntasList = preguntaController.queryAllPreguntas();
            for (PreguntaDTO pregunta : preguntasList) {
                if(pregunta.getCategoria().equals("creada")) {
                    preguntas.add(pregunta.getPregunta());
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar preguntas: " + e.getMessage());
        }
    }


}
