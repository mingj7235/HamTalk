package view;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KakaoMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Login");
		Parent root = FXMLLoader.load(KakaoMain.class.getResource("Login.fxml"));
		Scene scene = new Scene (root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		System.out.println("나는 고희광입니다.");
	}
	public static void main(String[] args) {
		launch(args);
	}
}
