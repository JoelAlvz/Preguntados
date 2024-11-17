package com.app.preguntados;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PreguntadosApplication extends Application {
	private static Stage primaryStage;
	@Override
	public void start(Stage stage) throws Exception {
		primaryStage = stage;
		showLoginView();
	}
	public static void showLoginView() throws Exception {
		Parent root = FXMLLoader.load(PreguntadosApplication.class.getResource("LoginView.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Login");
		primaryStage.show();
	}

	public static void showMenuView() throws Exception {
		Parent root = FXMLLoader.load(PreguntadosApplication.class.getResource("MenuView.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Menú Principal");
		primaryStage.show();
	}
	public static void main(String[] args) {
		SpringApplication.run(PreguntadosApplication.class, args);
		launch(args);
	}

}
