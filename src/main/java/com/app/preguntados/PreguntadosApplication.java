package com.app.preguntados;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PreguntadosApplication extends Application {
	private static ApplicationContext springContext;
	private static Stage primaryStage;

	@Override
	public void init() throws Exception {
		// Inicializa el contexto de Spring
		springContext = SpringApplication.run(PreguntadosApplication.class);
	}

	@Override
	public void start(Stage stage) throws Exception {
		primaryStage = stage;
		showLoginView();
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

	public static void showMenuView() throws Exception {
		FXMLLoader loader = new FXMLLoader(PreguntadosApplication.class.getResource("MenuView.fxml"));
		// Configurar controlador usando Spring
		loader.setControllerFactory(springContext::getBean);
		Parent root = loader.load();

		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Menú Principal");
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

	public static void main(String[] args) {
		launch(args);
	}
}
