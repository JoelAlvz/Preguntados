package com.app.preguntados;

import com.app.preguntados.controller.front.JuegoController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class PreguntadosApplication extends Application {
	private static ApplicationContext springContext;
	private static Stage primaryStage;

	@Override
	public void init() throws Exception {
		try {
			ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "start.bat");
			processBuilder.start();
			System.out.println("Script start.bat ejecutado correctamente.");
		} catch (IOException e) {
			System.err.println("Error al ejecutar el script start.bat: " + e.getMessage());
		}
		// Inicializa el contexto de Spring
		springContext = SpringApplication.run(PreguntadosApplication.class);
	}

	@Override
	public void start(Stage stage) throws Exception {
		primaryStage = stage;

		primaryStage.setOnCloseRequest(event -> {
			Platform.exit();
			System.exit(0);
		});

		showListaUsarioView();
	}

	public static void showLoginView() throws Exception {
		FXMLLoader loader = new FXMLLoader(PreguntadosApplication.class.getResource("LoginView.fxml"));
		// Configurar controlador usando Spring
		loader.setControllerFactory(springContext::getBean);
		Parent root = loader.load();

		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Login");
		primaryStage.show();
	}
	public static void showFinJuegoView() throws Exception {
			FXMLLoader loader = new FXMLLoader(PreguntadosApplication.class.getResource("FinJuegoView.fxml"));
		// Configurar controlador usando Spring
		loader.setControllerFactory(springContext::getBean);
		Parent root = loader.load();

		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Fin del juego");
		primaryStage.show();
	}
	public static void showBorrarPreguntaView() throws Exception {
		FXMLLoader loader = new FXMLLoader(PreguntadosApplication.class.getResource("BorrarPreguntaView.fxml"));
		// Configurar controlador usando Spring
		loader.setControllerFactory(springContext::getBean);
		Parent root = loader.load();

		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Borrar pregunta");
		primaryStage.show();
	}
	public static void showMenuView() throws Exception {
		FXMLLoader loader = new FXMLLoader(PreguntadosApplication.class.getResource("MenuView.fxml"));
		// Configurar controlador usando Spring
		loader.setControllerFactory(springContext::getBean);
		Parent root = loader.load();

		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Menú Principal");
		primaryStage.show();
	}
	public static void showModoJuegoView() throws Exception {
		FXMLLoader loader = new FXMLLoader(PreguntadosApplication.class.getResource("ModoJuegoView.fxml"));
		// Configurar controlador usando Spring
		loader.setControllerFactory(springContext::getBean);
		Parent root = loader.load();

		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Menú Modo Juego");
		primaryStage.show();
	}
	public static void showNuevasPreguntasView() throws Exception {
		FXMLLoader loader = new FXMLLoader(PreguntadosApplication.class.getResource("NuevasPreguntas.fxml"));
		// Configurar controlador usando Spring
		loader.setControllerFactory(springContext::getBean);
		Parent root = loader.load();

		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Preguntas");
		primaryStage.show();

	}
	public static void showRegisterView() throws Exception {
		FXMLLoader loader = new FXMLLoader(PreguntadosApplication.class.getResource("RegistroView.fxml"));
		// Configurar controlador usando Spring
		loader.setControllerFactory(springContext::getBean);
		Parent root = loader.load();

		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Registro");
		primaryStage.show();
	}
	public static void showContadorView() throws Exception {
		FXMLLoader loader = new FXMLLoader(PreguntadosApplication.class.getResource("ModoJuegoContador.fxml"));
		// Configurar controlador usando Spring
		loader.setControllerFactory(springContext::getBean);
		Parent root = loader.load();

		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Contador");
		primaryStage.show();
	}
	public static void showListaUsarioView() throws Exception {
		FXMLLoader loader = new FXMLLoader(PreguntadosApplication.class.getResource("ListaUsuario.fxml"));
		// Configurar controlador usando Spring
		loader.setControllerFactory(springContext::getBean);
		Parent root = loader.load();

		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Lista de usuarios");
		primaryStage.show();
	}
	public static void showJuegoView() throws Exception {
		FXMLLoader loader = new FXMLLoader(PreguntadosApplication.class.getResource("JuegoView.fxml"));
		// Configurar controlador usando Spring
		loader.setControllerFactory(springContext::getBean);
		Parent root = loader.load();

		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Juego");
		primaryStage.show();
		JuegoController.initGraphics();

	}
	@Override
	public void stop() throws Exception {
		// Detener recursos cuando la aplicación se cierra
		if (springContext != null) {
			System.out.println("Cerrando el contexto de Spring...");
			SpringApplication.exit(springContext);
		}
		Platform.exit();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
