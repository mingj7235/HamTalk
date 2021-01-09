package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Server extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Server");
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("Server.fxml"));
		Scene scene = new Scene (root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
