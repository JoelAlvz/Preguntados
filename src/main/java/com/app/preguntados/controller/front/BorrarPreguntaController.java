package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import com.app.preguntados.controller.PreguntaController;
import com.app.preguntados.model.dto.PreguntaDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BorrarPreguntaController{

    @Autowired
    private PreguntaController preguntaController;
    @FXML
    private ListView<String> listaPreguntas;

    private ObservableList<String> preguntas;

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

    @FXML
    public void initialize() {
        preguntas = FXCollections.observableArrayList();
        listaPreguntas.setItems(preguntas);
        cargarPreguntas();
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
