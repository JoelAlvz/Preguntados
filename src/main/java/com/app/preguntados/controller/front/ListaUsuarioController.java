package com.app.preguntados.controller.front;

import com.app.preguntados.PreguntadosApplication;
import com.app.preguntados.controller.UsuarioController;
import com.app.preguntados.model.dto.UsuarioDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class ListaUsuarioController {
    @Autowired
    private UsuarioController usuarioController;

    @Autowired
    private UsuarioActual usuarioActual;

    @FXML
    private ListView<String> lista;

    private ObservableList<String> usuarios;


    @FXML
    public void initialize() {
        usuarios = FXCollections.observableArrayList();
        lista.setItems(usuarios);
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        try {
            List<UsuarioDTO> usuariosList = usuarioController.queryAllUsuarios();
            for (UsuarioDTO usuario : usuariosList) {
                usuarios.add(usuario.getNombre());
            }
        } catch (Exception e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }
    }
    @FXML
    public void eliminarUsuario() {
        String seleccionado = lista.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                UsuarioDTO usuarioDTO = new UsuarioDTO();
                usuarioDTO.setNombre(seleccionado);
                usuarioController.deleteUsuarioByNombre(seleccionado);

                // Eliminar de la lista en la interfaz
                usuarios.remove(seleccionado);
                lista.setItems(usuarios);
            } catch (Exception e) {
                System.err.println("Error al eliminar usuario: " + e.getMessage());
            }
        }
    }

    public void escogerUsuario(ActionEvent actionEvent) throws Exception {
        String seleccionado = lista.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            List<UsuarioDTO> usuarios = usuarioController.queryAllUsuarios();
            for (UsuarioDTO usuario : usuarios) {
                if (usuario.getNombre().equals(seleccionado)) {
                    usuarioActual.setUsuario(usuario);
                }
            }
            PreguntadosApplication.showMenuView();
        }
    }

    public void nuevoUsuario(ActionEvent actionEvent) throws Exception {
        PreguntadosApplication.showRegisterView();
    }
}
